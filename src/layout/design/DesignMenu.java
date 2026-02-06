package layout.design;

import layout.constants.HangFonts;
import layout.constants.HangImages;

import javax.swing.*;
import java.awt.*;

/* This Java file contains the custom design for main menu abstracting it away from the MainMenu.java file */
/* Typically only centerPanel is overridden */
public class DesignMenu {
    public static void displayMenuButtons(JPanel centerPanel) {
        centerPanel.setLayout(new BorderLayout());

        JPanel menuButtons = new JPanel(new GridBagLayout());
        menuButtons.setOpaque(false);
        menuButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        JButton htpButton = addCustomButton("How To Play", HangImages.htpIcon);
        gbc.gridy = 0;
        menuButtons.add(htpButton, gbc);

        JButton startButton = addCustomButton("Start", HangImages.startIcon);
        gbc.gridy = 1;
        menuButtons.add(startButton, gbc);

        // Nested container for achieving SW direction
        JPanel southWrapper = new JPanel(new BorderLayout());
        southWrapper.setOpaque(false);
        southWrapper.add(menuButtons, BorderLayout.WEST);

        centerPanel.add(southWrapper, BorderLayout.SOUTH);
    }

    // Create a function that customizes the button
    public static JButton addCustomButton(String buttonName, Image img) {
        JButton customButton = new JButton(buttonName);

        int scale = 50;
        Image scaledIcon = img.getScaledInstance(scale, scale, Image.SCALE_SMOOTH);
        customButton.setIcon(new ImageIcon(scaledIcon));

        // Text layout
        customButton.setHorizontalTextPosition(JButton.CENTER);
        customButton.setVerticalTextPosition(JButton.BOTTOM);
        customButton.setFont(HangFonts.loadCustomFonts(Font.PLAIN, 16));
        customButton.setForeground(Color.WHITE);

        // Default button layout
        customButton.setContentAreaFilled(false);
        customButton.setBorderPainted(false);
        customButton.setFocusPainted(false);

        return customButton;
    }
}
