package Gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class ExamQuestionPanel extends JPanel {
    private final JTextArea questionArea;
    private final JPanel answerPanel;
    private final JButton submitButton;
    private final JButton nextButton;
    private final JLabel feedbackLabel;
    private final java.util.List<Question> questions;
    private int currentQuestionIndex = 0;
    private Component currentAnswerComponent;
    private final JLabel questionNumberLabel;

    public ExamQuestionPanel(Runnable onComplete) {
        questions = Questions.getRandomQuestions(4); // Get 4 random questions

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(45, 45, 50));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Question number and navigation
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setBackground(new Color(45, 45, 50));
        questionNumberLabel = new JLabel("Question 1 of " + questions.size());
        questionNumberLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        questionNumberLabel.setForeground(new Color(200, 200, 210));
        headerPanel.add(questionNumberLabel, BorderLayout.WEST);

        JLabel subjectLabel = new JLabel(questions.get(0).getSubject());
        subjectLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        subjectLabel.setForeground(new Color(130, 160, 255));
        headerPanel.add(subjectLabel, BorderLayout.EAST);
        add(headerPanel, BorderLayout.NORTH);

        // Question area
        questionArea = new JTextArea();
        questionArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        questionArea.setEditable(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setBackground(new Color(55, 55, 60));
        questionArea.setForeground(new Color(220, 220, 225));
        questionArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane questionScroll = new JScrollPane(questionArea);
        questionScroll.setBorder(new CompoundBorder(
            new LineBorder(new Color(60, 60, 65), 1, true),
            new EmptyBorder(5, 5, 5, 5)
        ));
        add(questionScroll, BorderLayout.CENTER);

        // Answer panel
        answerPanel = new JPanel(new BorderLayout(10, 10));
        answerPanel.setBackground(new Color(45, 45, 50));
        add(answerPanel, BorderLayout.SOUTH);

        // Feedback label
        feedbackLabel = new JLabel();
        feedbackLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        feedbackLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackLabel.setVisible(false);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(new Color(45, 45, 50));

        submitButton = createButton("Submit Answer", new Color(40, 120, 80));
        nextButton = createButton("Next Question →", new Color(60, 80, 170));
        nextButton.setVisible(false);

        buttonPanel.add(feedbackLabel);
        buttonPanel.add(submitButton);
        buttonPanel.add(nextButton);

        answerPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Initialize first question
        displayCurrentQuestion();

        // Button actions
        submitButton.addActionListener(e -> handleSubmit());
        nextButton.addActionListener(e -> {
            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                displayCurrentQuestion();
            } else {
                onComplete.run();
            }
        });
    }

    private JButton createButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(35, 35, 40));
        button.setForeground(Color.BLACK);
        button.setBorder(new CompoundBorder(
            new LineBorder(new Color(45, 45, 50), 1),
            new EmptyBorder(8, 15, 8, 15)
        ));
        button.setFocusPainted(false);

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(45, 45, 50));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(35, 35, 40));
            }
        });

        return button;
    }

    private void displayCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionNumberLabel.setText(String.format("Question %d of %d", currentQuestionIndex + 1, questions.size()));
        questionArea.setText(currentQuestion.getFormattedQuestion());

        if (currentAnswerComponent != null) {
            answerPanel.remove(currentAnswerComponent);
        }

        currentAnswerComponent = currentQuestion.createAnswerComponent();
        currentAnswerComponent.setBackground(new Color(55, 55, 60));
        if (currentAnswerComponent instanceof JScrollPane) {
            ((JScrollPane) currentAnswerComponent).getViewport().setBackground(new Color(55, 55, 60));
            JTextArea textArea = (JTextArea) ((JScrollPane) currentAnswerComponent).getViewport().getView();
            textArea.setBackground(new Color(55, 55, 60));
            textArea.setForeground(new Color(220, 220, 225));
            textArea.setCaretColor(new Color(220, 220, 225));
        } else if (currentAnswerComponent instanceof JPanel) {
            Component[] components = ((JPanel) currentAnswerComponent).getComponents();
            for (Component comp : components) {
                if (comp instanceof JRadioButton) {
                    comp.setBackground(new Color(55, 55, 60));
                    comp.setForeground(new Color(220, 220, 225));
                }
            }
        }

        answerPanel.add(currentAnswerComponent, BorderLayout.CENTER);
        answerPanel.revalidate();
        answerPanel.repaint();

        submitButton.setVisible(true);
        nextButton.setVisible(false);
        feedbackLabel.setVisible(false);
    }

    private void handleSubmit() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        String answer = getAnswer();
        boolean isCorrect = currentQuestion.checkAnswer(answer);

        feedbackLabel.setVisible(true);
        if (isCorrect) {
            feedbackLabel.setText("✓ Correct!");
            feedbackLabel.setForeground(new Color(100, 200, 100));
        } else {
            feedbackLabel.setText("✗ Incorrect");
            feedbackLabel.setForeground(new Color(200, 100, 100));
        }

        // Show explanation
        questionArea.setText(currentQuestion.getFormattedQuestion() + "\n\nExplanation:\n" + currentQuestion.getExplanation());

        submitButton.setVisible(false);
        nextButton.setVisible(true);

        // Disable answer component
        if (currentAnswerComponent instanceof JScrollPane) {
            ((JTextArea)((JScrollPane)currentAnswerComponent).getViewport().getView()).setEditable(false);
        } else if (currentAnswerComponent instanceof JPanel) {
            Component[] components = ((JPanel)currentAnswerComponent).getComponents();
            for (Component comp : components) {
                comp.setEnabled(false);
            }
        }
    }

    private String getAnswer() {
        if (currentAnswerComponent instanceof JScrollPane) {
            return ((JTextArea)((JScrollPane)currentAnswerComponent).getViewport().getView()).getText();
        } else if (currentAnswerComponent instanceof JPanel) {
            ButtonGroup group = new ButtonGroup();
            for (Component comp : ((JPanel)currentAnswerComponent).getComponents()) {
                if (comp instanceof JRadioButton) {
                    JRadioButton radio = (JRadioButton)comp;
                    if (radio.isSelected()) {
                        return radio.getActionCommand();
                    }
                }
            }
        }
        return "";
    }

    public void setAnswerEditable(boolean editable) {
        if (currentAnswerComponent != null) {
            if (currentAnswerComponent instanceof JScrollPane) {
                ((JTextArea)((JScrollPane)currentAnswerComponent).getViewport().getView()).setEditable(editable);
            } else if (currentAnswerComponent instanceof JPanel) {
                Component[] components = ((JPanel)currentAnswerComponent).getComponents();
                for (Component comp : components) {
                    comp.setEnabled(editable);
                }
            }
            submitButton.setEnabled(editable);
        }
    }
}