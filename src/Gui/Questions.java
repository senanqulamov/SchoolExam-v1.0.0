package Gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Questions {
    private static final java.util.List<Question> questionBank = new ArrayList<>();

    static {
        // Initialize question bank
        questionBank.add(new TextQuestion(
            "Algebra",
            "Solve the equation: 2x + 3 = 11",
            "x = 4",
            "Step 1: Subtract 3 from both sides: 2x = 8\n" +
            "Step 2: Divide both sides by 2: x = 4"
        ));

        questionBank.add(new ChoiceQuestion(
            "Geometry",
            "What is the area of a circle with radius 5 units?",
            "78.54",
            Arrays.asList(
                "65.36",
                "78.54",
                "25π",
                "125.66"
            ),
            1,
            "Area = πr² = π(5)² = 78.54 square units"
        ));

        questionBank.add(new TextQuestion(
            "Calculus",
            "Find the derivative of f(x) = x² + 3x",
            "f'(x) = 2x + 3",
            "Using power rule and sum rule:\n" +
            "d/dx(x²) = 2x\n" +
            "d/dx(3x) = 3\n" +
            "Therefore, f'(x) = 2x + 3"
        ));

        questionBank.add(new ChoiceQuestion(
            "Trigonometry",
            "What is the value of sin(90°)?",
            "1",
            Arrays.asList(
                "0",
                "1",
                "-1",
                "undefined"
            ),
            1,
            "sin(90°) = 1 is one of the fundamental trigonometric values"
        ));
    }

    public static java.util.List<Question> getRandomQuestions(int count) {
        if (questionBank.isEmpty()) {
            throw new IllegalStateException("Question bank is empty!");
        }
        java.util.List<Question> questions = new ArrayList<>(questionBank);
        Collections.shuffle(questions);
        return questions.subList(0, Math.min(count, questions.size()));
    }
}

abstract class Question {
    protected String subject;
    protected String questionText;
    protected String correctAnswer;
    protected String explanation;
    protected QuestionType type;

    public Question(String subject, String questionText, String correctAnswer, String explanation, QuestionType type) {
        this.subject = subject;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.explanation = explanation;
        this.type = type;
    }

    public abstract boolean checkAnswer(String answer);
    public abstract String getFormattedQuestion();
    public abstract Component createAnswerComponent();

    public String getExplanation() {
        return explanation;
    }

    public QuestionType getType() {
        return type;
    }

    public String getSubject() {
        return subject;
    }
}

class TextQuestion extends Question {
    public TextQuestion(String subject, String questionText, String correctAnswer, String explanation) {
        super(subject, questionText, correctAnswer, explanation, QuestionType.TEXT);
    }

    @Override
    public boolean checkAnswer(String answer) {
        return answer.trim().equalsIgnoreCase(correctAnswer.trim());
    }

    @Override
    public String getFormattedQuestion() {
        return questionText;
    }

    @Override
    public Component createAnswerComponent() {
        JTextArea area = new JTextArea(6, 40);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        return new JScrollPane(area);
    }
}

class ChoiceQuestion extends Question {
    private final java.util.List<String> choices;
    private final int correctIndex;

    public ChoiceQuestion(String subject, String questionText, String correctAnswer,
                         java.util.List<String> choices, int correctIndex, String explanation) {
        super(subject, questionText, correctAnswer, explanation, QuestionType.CHOICE);
        this.choices = choices;
        this.correctIndex = correctIndex;
    }

    @Override
    public boolean checkAnswer(String answer) {
        try {
            int selectedIndex = Integer.parseInt(answer);
            return selectedIndex == correctIndex;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public String getFormattedQuestion() {
        StringBuilder sb = new StringBuilder(questionText + "\n\n");
        for (int i = 0; i < choices.size(); i++) {
            sb.append(String.format("%d) %s\n", i, choices.get(i)));
        }
        return sb.toString();
    }

    @Override
    public Component createAnswerComponent() {
        JPanel panel = new JPanel(new GridLayout(0, 1));
        ButtonGroup group = new ButtonGroup();

        for (int i = 0; i < choices.size(); i++) {
            JRadioButton radio = new JRadioButton(choices.get(i));
            radio.setActionCommand(String.valueOf(i));
            radio.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            group.add(radio);
            panel.add(radio);
        }

        return panel;
    }

    public java.util.List<String> getChoices() {
        return choices;
    }
}

enum QuestionType {
    TEXT,
    CHOICE
}
