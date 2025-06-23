package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ExamHeader extends JPanel {
    private final JLabel timerLabel;
    private final Color startColor = new Color(28, 30, 35);
    private final Color endColor = new Color(35, 38, 45);

    public ExamHeader(String title, int minutes) {
        setLayout(new BorderLayout(20, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Left panel with logo and student info
        JPanel leftPanel = new JPanel(new BorderLayout(10, 5));
        leftPanel.setOpaque(false);

        // Modern avatar circle
        JPanel avatarPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(82, 130, 255));
                g2d.fillOval(0, 0, 48, 48);
                g2d.setColor(new Color(200, 200, 200));
                g2d.setFont(new Font("Segoe UI", Font.BOLD, 20));
                g2d.drawString("JD", 12, 30);
            }
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(48, 48);
            }
        };
        leftPanel.add(avatarPanel, BorderLayout.WEST);

        JPanel studentInfo = new JPanel(new GridLayout(2, 1, 2, 2));
        studentInfo.setOpaque(false);
        JLabel nameLabel = new JLabel("John Doe");
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(new Color(230, 230, 230));
        JLabel idLabel = new JLabel("ID: 12345 • Grade 12");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idLabel.setForeground(new Color(180, 180, 180));
        studentInfo.add(nameLabel);
        studentInfo.add(idLabel);
        leftPanel.add(studentInfo, BorderLayout.CENTER);

        add(leftPanel, BorderLayout.WEST);

        // Center title with modern styling
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titleLabel.setForeground(new Color(230, 230, 230));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.CENTER);

        // Timer panel with modern styling
        JPanel timerPanel = new JPanel(new BorderLayout(5, 0));
        timerPanel.setOpaque(false);

        timerLabel = new JLabel(String.format("%02d:00", minutes));
        timerLabel.setFont(new Font("JetBrains Mono", Font.BOLD, 24));
        timerLabel.setForeground(new Color(82, 130, 255));

        // Add timer icon
        JLabel timerIcon = new JLabel("⏱");
        timerIcon.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        timerIcon.setForeground(new Color(180, 180, 180));

        timerPanel.add(timerIcon, BorderLayout.WEST);
        timerPanel.add(timerLabel, BorderLayout.CENTER);

        // Wrap timer in a rounded panel
        JPanel timerContainer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(45, 48, 55));
                g2d.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 15, 15));
            }
        };
        timerContainer.setLayout(new BorderLayout());
        timerContainer.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        timerContainer.add(timerPanel);
        timerContainer.setOpaque(false);

        add(timerContainer, BorderLayout.EAST);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        GradientPaint gp = new GradientPaint(0, 0, startColor, getWidth(), 0, endColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setTime(int secondsLeft) {
        int min = secondsLeft / 60;
        int sec = secondsLeft % 60;
        timerLabel.setText(String.format("%02d:%02d", min, sec));
        if (secondsLeft <= 300) {
            timerLabel.setForeground(new Color(255, 85, 85));
        }
    }
}