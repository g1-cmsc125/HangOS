package layout.design;

import layout.constants.StartButton;

import javax.swing.*;
import java.awt.*;

public class Design {
    public static int screenWidth = 720;
    public static int screenHeight = 512;

    // Default center design containing WindowsXP bg
    // Reusable for other pages needing the same bg
    public static void centerDesignDefault(JPanel mainPanel, JPanel centerPanel) {
        centerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.90)));
        centerPanel.setOpaque(false);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    // Dynamic footer design
    // Reusable and modifiable (to be added)
    // 10% start, 80% task, 10% time
    public static void footerDesign(JPanel mainPanel) {
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        gbc.gridheight = 1;

        addStartButton(bottomPanel, gbc);
        addTaskBar(bottomPanel, gbc);
        addTimePanel(bottomPanel, gbc);

        bottomPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.10)));
        bottomPanel.setOpaque(true);
        bottomPanel.setBackground(new Color(31, 107, 229));
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    // Separate colors in constants/Color.java
    private static void addStartButton(JPanel bottomPanel, GridBagConstraints gbc) {
        StartButton startButton = new StartButton();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.05;
        bottomPanel.add(startButton, gbc);
    }

    private static void addTaskBar(JPanel bottomPanel, GridBagConstraints gbc) {
        JPanel blueTaskbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        blueTaskbar.setOpaque(false);

        for(int i = 0; i < 3; i++) {
            JPanel task = new JPanel();
            task.setPreferredSize(new Dimension(50, 20));
            task.setBackground(Color.pink);
            blueTaskbar.add(task);
        }

        gbc.gridx = 1; gbc.weightx = 0.80;
        bottomPanel.add(blueTaskbar, gbc);
    }

    private static  void addTimePanel(JPanel bottomPanel, GridBagConstraints gbc) {
        JPanel timeContainer = new JPanel();
        timeContainer.setBackground(new Color(18, 151, 229)); // Lighter blue
        gbc.gridx = 2; gbc.weightx = 0.10;
        bottomPanel.add(timeContainer, gbc);
    }
}