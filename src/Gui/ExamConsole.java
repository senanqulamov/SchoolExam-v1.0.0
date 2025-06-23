package Gui;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ExamConsole extends JPanel {
    private final JTextPane consolePane;
    private final JTextField inputField;
    private final StyledDocument doc;
    private final Style defaultStyle, infoStyle, warnStyle, errorStyle, promptStyle;

    public ExamConsole(ActionListener inputHandler) {
        setLayout(new BorderLayout(0, 5));
        setBackground(new Color(30, 30, 35));
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(50, 50, 55), 1),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Console header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(40, 40, 45));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JLabel titleLabel = new JLabel("System Console");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        titleLabel.setForeground(new Color(200, 200, 200));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        add(headerPanel, BorderLayout.NORTH);

        // Console output
        consolePane = new JTextPane();
        consolePane.setEditable(false);
        consolePane.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
        consolePane.setBackground(new Color(25, 25, 30));
        consolePane.setForeground(new Color(200, 200, 200));
        consolePane.setMargin(new Insets(10, 10, 10, 10));
        doc = consolePane.getStyledDocument();

        // Styles
        defaultStyle = consolePane.addStyle("Default", null);
        StyleConstants.setForeground(defaultStyle, new Color(200, 200, 200));

        infoStyle = consolePane.addStyle("Info", null);
        StyleConstants.setForeground(infoStyle, new Color(100, 180, 255));

        warnStyle = consolePane.addStyle("Warn", null);
        StyleConstants.setForeground(warnStyle, new Color(255, 180, 0));

        errorStyle = consolePane.addStyle("Error", null);
        StyleConstants.setForeground(errorStyle, new Color(255, 100, 100));

        promptStyle = consolePane.addStyle("Prompt", null);
        StyleConstants.setForeground(promptStyle, new Color(100, 255, 100));
        StyleConstants.setBold(promptStyle, true);

        JScrollPane scrollPane = new JScrollPane(consolePane);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(consolePane.getBackground());
        add(scrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout(5, 0));
        inputPanel.setBackground(new Color(35, 35, 40));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));

        JLabel promptLabel = new JLabel(">");
        promptLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 13));
        promptLabel.setForeground(new Color(100, 255, 100));
        promptLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        inputPanel.add(promptLabel, BorderLayout.WEST);

        inputField = new JTextField();
        inputField.setFont(new Font("JetBrains Mono", Font.PLAIN, 13));
        inputField.setBackground(new Color(45, 45, 50));
        inputField.setForeground(new Color(200, 200, 200));
        inputField.setCaretColor(new Color(200, 200, 200));
        inputField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        inputField.addActionListener(inputHandler);
        inputPanel.add(inputField, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.SOUTH);
    }

    public void print(String msg) {
        appendToPane(msg + "\n", defaultStyle);
    }

    public void printInfo(String msg) {
        appendToPane(msg + "\n", infoStyle);
    }

    public void printWarning(String msg) {
        appendToPane("⚠ " + msg + "\n", warnStyle);
    }

    public void printError(String msg) {
        appendToPane("⛔ " + msg + "\n", errorStyle);
    }

    public void printPrompt(String cmd) {
        appendToPane("> " + cmd + "\n", promptStyle);
    }

    private void appendToPane(String msg, Style style) {
        try {
            doc.insertString(doc.getLength(), msg, style);
            consolePane.setCaretPosition(doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public String consumeInput() {
        String input = inputField.getText();
        inputField.setText("");
        return input;
    }

    public void clear() {
        consolePane.setText("");
    }

    public void setInputEditable(boolean editable) {
        inputField.setEditable(editable);
    }
}