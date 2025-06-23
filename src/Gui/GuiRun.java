package Gui;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GuiRun {

    private static ExamConsole examConsole;
    private static ExamHeader examHeader;
    private static ExamQuestionPanel questionPanel;
    private static Timer examTimer;
    private static int timeLeft = 60 * 60; // 1 hour in seconds
    private static JFrame mainFrame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static boolean examStarted = false;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(GuiRun::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        mainFrame = new JFrame("School Exam Application");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 800);
        mainFrame.setMinimumSize(new Dimension(800, 600));
        mainFrame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainFrame.add(mainPanel);

        // Welcome screen
        createWelcomeScreen();

        // Instructions screen
        createInstructionsScreen();

        // Main exam screen
        createExamScreen();

        // Show welcome screen first
        cardLayout.show(mainPanel, "welcome");
        mainFrame.setVisible(true);
    }

    private static void createWelcomeScreen() {
        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setBackground(new Color(28, 30, 35));
        GridBagConstraints gbc = new GridBagConstraints();

        // Logo/Icon
        JLabel iconLabel = new JLabel("📚");
        iconLabel.setFont(new Font("Segoe UI", Font.PLAIN, 72));
        iconLabel.setForeground(new Color(82, 130, 255));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 30, 0);
        welcomePanel.add(iconLabel, gbc);

        // Welcome text
        JLabel welcomeLabel = new JLabel("School Exam System");
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        welcomeLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 20, 0);
        welcomePanel.add(welcomeLabel, gbc);

        // Student info panel
        JPanel infoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        infoPanel.setOpaque(false);

        addFormField(infoPanel, "Student ID:", "Enter your ID");
        addFormField(infoPanel, "Full Name:", "Enter your full name");
        addFormField(infoPanel, "Class:", "Enter your class");

        gbc.gridy = 2;
        gbc.insets = new Insets(20, 0, 30, 0);
        welcomePanel.add(infoPanel, gbc);

        // Start button
        JButton startButton = new JButton("Start Exam");
        styleButton(startButton, new Color(82, 130, 255));
        startButton.addActionListener(e -> cardLayout.show(mainPanel, "instructions"));

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        welcomePanel.add(startButton, gbc);

        mainPanel.add(welcomePanel, "welcome");
    }

    private static void createInstructionsScreen() {
        JPanel instructionsPanel = new JPanel(new GridBagLayout());
        instructionsPanel.setBackground(new Color(28, 30, 35));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 30, 10, 30);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel titleLabel = new JLabel("Exam Instructions");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        instructionsPanel.add(titleLabel, gbc);

        String[] instructions = {
            "• You have 60 minutes to complete the exam",
            "• There are 4 questions in total",
            "• You can use the console for calculations",
            "• Each question must be answered before moving to the next",
            "• You cannot return to previous questions",
            "• Submit button will be disabled when time is up",
            "• Use 'help' command in console for assistance"
        };

        JPanel instructionsList = new JPanel();
        instructionsList.setLayout(new BoxLayout(instructionsList, BoxLayout.Y_AXIS));
        instructionsList.setOpaque(false);

        for (String instruction : instructions) {
            JLabel label = new JLabel(instruction);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            label.setForeground(new Color(200, 200, 200));
            label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
            instructionsList.add(label);
        }

        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        instructionsPanel.add(instructionsList, gbc);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonsPanel.setOpaque(false);

        JButton backButton = new JButton("← Back");
        styleButton(backButton, new Color(0, 0, 0));
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "welcome"));

        JButton startExamButton = new JButton("Begin Exam →");
        styleButton(startExamButton, new Color(0, 0, 0));
        startExamButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "exam");
            startExam();
        });

        buttonsPanel.add(backButton);
        buttonsPanel.add(startExamButton);

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        instructionsPanel.add(buttonsPanel, gbc);

        mainPanel.add(instructionsPanel, "instructions");
    }

    private static void createExamScreen() {
        JPanel examPanel = new JPanel(new BorderLayout(0, 0));
        examPanel.setBackground(new Color(28, 30, 35));

        // Header with dark theme
        examHeader = new ExamHeader("Mathematics Final Exam - 2024", 60);
        examPanel.add(examHeader, BorderLayout.NORTH);

        // Main split pane with modern styling
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setBorder(null);
        splitPane.setResizeWeight(0.7);
        splitPane.setDividerSize(1);
        splitPane.setDividerLocation(0.7);
        splitPane.setBackground(new Color(28, 30, 35));

        // Question panel
        questionPanel = new ExamQuestionPanel(() -> {
            examConsole.printInfo("Exam completed!");
            examTimer.stop();
            questionPanel.setAnswerEditable(false);
            examConsole.setInputEditable(false);
        });
        splitPane.setLeftComponent(questionPanel);

        // Console panel
        examConsole = new ExamConsole(e -> {
            String input = examConsole.consumeInput();
            if (!input.trim().isEmpty()) {
                examConsole.printPrompt(input);
                handleCommand(input);
            }
        });
        splitPane.setRightComponent(examConsole);

        examPanel.add(splitPane, BorderLayout.CENTER);

        // Modern status bar
        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBackground(new Color(35, 38, 45));
        statusBar.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        JPanel statusContent = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        statusContent.setOpaque(false);

        // Status indicator
        JPanel indicator = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(82, 230, 82));
                g2d.fillOval(0, 0, 8, 8);
            }
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(8, 8);
            }
        };

        JLabel statusLabel = new JLabel("Connected to exam server");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        statusLabel.setForeground(new Color(180, 180, 180));

        statusContent.add(indicator);
        statusContent.add(statusLabel);
        statusBar.add(statusContent, BorderLayout.WEST);

        examPanel.add(statusBar, BorderLayout.SOUTH);

        mainPanel.add(examPanel, "exam");
    }

    private static void startExam() {
        if (!examStarted) {
            examStarted = true;
            startExamTimer();
            examConsole.printInfo("Welcome to the School Exam App.");
            examConsole.printInfo("You have 1 hour to complete the exam.");
            examConsole.printInfo("Type 'help' in the console for available commands.");
        }
    }

    private static void addFormField(JPanel panel, String labelText, String placeholder) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        label.setForeground(new Color(200, 200, 200));

        JTextField field = new JTextField(20);
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(new Color(45, 48, 55));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 63, 70)),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.putClientProperty("JTextField.placeholderText", placeholder);

        panel.add(label);
        panel.add(field);
    }

    private static void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(new Color(0, 0, 255));
        button.setForeground(Color.BLACK);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(45, 45, 50)),
            BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(45, 45, 50));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(35, 35, 40));
            }
        });
    }

    private static void handleCommand(String input) {
        switch (input.trim().toLowerCase()) {
            case "help":
                examConsole.printInfo("Available commands:");
                examConsole.print("- help: Show this help menu");
                examConsole.print("- clear: Clear the console");
                examConsole.print("- time: Show remaining time");
                break;
            case "clear":
                examConsole.clear();
                break;
            case "time":
                int min = timeLeft / 60;
                int sec = timeLeft % 60;
                examConsole.printInfo(String.format("Time left: %02d:%02d", min, sec));
                break;
            default:
                examConsole.printError("Unknown command: " + input);
        }
    }

    private static void startExamTimer() {
        examTimer = new Timer(1000, e -> {
            if (timeLeft > 0) {
                timeLeft--;
                examHeader.setTime(timeLeft);
                if (timeLeft == 300) { // 5 minutes left warning
                    examConsole.printWarning("Only 5 minutes left!");
                }
            } else {
                examHeader.setTime(0);
                examConsole.printError("Time is up! Your exam will be submitted automatically.");
                examTimer.stop();
                questionPanel.setAnswerEditable(false);
                examConsole.setInputEditable(false);
            }
        });
        examTimer.start();
    }
}