package layout.design;

import layout.Card;
import layout.constants.HangColors;
import layout.constants.HangFonts;
import layout.constants.HangImages;
import layout.constants.MiniWindow; // Ensure this is imported

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DesignMenu {
    // Displays license, abd, htp icons
    public static void displayMenuButtons(JPanel centerPanel) {
        centerPanel.setLayout(new BorderLayout());

        JPanel menuButtons = new JPanel(new GridBagLayout());
        menuButtons.setOpaque(false);
        menuButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, HangFonts.regularFontSize, 0);

        // 1. TOP POSITION (License)
        JButton licenseButton = addCustomButton("License", HangImages.licenseIcon, centerPanel);
        gbc.gridy = 0;
        menuButtons.add(licenseButton, gbc);

        // 2. MIDDLE-TOP (Devs)
        JButton adButton = addCustomButton("The Devs", HangImages.devsIcon, centerPanel);
        gbc.gridy = 1;
        menuButtons.add(adButton, gbc);

        // 3. MIDDLE-BOTTOM (How to Play)
        JButton htpButton = addCustomButton("How To Play", HangImages.htpIcon, centerPanel);
        gbc.gridy = 2;
        menuButtons.add(htpButton, gbc);

        // 4. BOTTOM POSITION (Start)
        JButton startButton = addCustomButton("Start", HangImages.startIcon, centerPanel);
        gbc.gridy = 3;
        menuButtons.add(startButton, gbc);

        JPanel southWrapper = new JPanel(new BorderLayout());
        southWrapper.setOpaque(false);
        southWrapper.add(menuButtons, BorderLayout.WEST);

        centerPanel.add(southWrapper, BorderLayout.SOUTH);
    }

    public static JButton addCustomButton(String buttonName, Image img, JPanel centerPanel) {
        JButton customButton = new JButton(buttonName);

        // Define a fixed square size for ALL icons
        int iconSize = (int)(HangFonts.titleFontSize * 1.5);

        // Force the image into a square iconSize x iconSize
        Image scaledIcon = img.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        customButton.setIcon(new ImageIcon(scaledIcon));

        // Ensure the button itself doesn't shrink-wrap too tightly
        customButton.setPreferredSize(new Dimension(iconSize + 40, iconSize + 40));

        customButton.setHorizontalTextPosition(JButton.CENTER);
        customButton.setVerticalTextPosition(JButton.BOTTOM);
        customButton.setIconTextGap(5);

        customButton.setFont(HangFonts.loadCustomFonts(Font.PLAIN, HangFonts.regularFontSize));
        customButton.setForeground(Color.WHITE);

        customButton.setContentAreaFilled(false);
        customButton.setBorderPainted(false);
        customButton.setFocusPainted(false);

        customButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Define a clean, blank-slate panel for the window content
                JPanel inWindowPanel = new JPanel(new BorderLayout());
                animateMiniWindow(centerPanel, buttonName, inWindowPanel, customButton);
            }
        });

        return customButton;
    }

    private static void animateMiniWindow(JPanel centerPanel, String title, JPanel inWindowPanel, JButton sourceButton) {
        final int TARGET_SIZE = 245;

        // Clean up existing popups
        for (Component c : centerPanel.getComponents()) {
            if (c.getName() != null && c.getName().equals("PopupLayer")) {
                centerPanel.remove(c);
            }
        }

        // Setup the Popup Layer
        JPanel popupLayer = new JPanel(null);
        popupLayer.setName("PopupLayer");
        popupLayer.setOpaque(false);
        popupLayer.setBounds(0, 0, centerPanel.getWidth(), centerPanel.getHeight());

        // GET STARTING POSITION
        // Convert button's (0,0) to centerPanel coordinates
        Point startPoint = SwingUtilities.convertPoint(sourceButton, 0, 0, centerPanel);
        int startX = startPoint.x;
        int startY = startPoint.y;

        // GET ENDING POSITION (South-East Anchor)
        int endX = centerPanel.getWidth() - TARGET_SIZE - 30;
        int endY = centerPanel.getHeight() - TARGET_SIZE - 80;

        MiniWindow mw = new MiniWindow(title, 0, 0, inWindowPanel);
        popupLayer.add(mw);
        centerPanel.add(popupLayer);
        centerPanel.setComponentZOrder(popupLayer, 0);

        // ANIMATION TIMER
        Timer timer = new Timer(15, null);
        timer.addActionListener(new ActionListener() {
            double t = 0; // Animation progress from 0.0 to 1.0

            @Override
            public void actionPerformed(ActionEvent e) {
                t += 0.08; // Control speed here

                if (t >= 1.0) {
                    mw.setBounds(endX, endY, TARGET_SIZE, TARGET_SIZE);
                    mw.validate();
                    timer.stop();
                } else {
                    // Linear interpolation for movement and size
                    int currentSize = (int) (TARGET_SIZE * t);
                    int currentX = (int) (startX + (endX - startX) * t);
                    int currentY = (int) (startY + (endY - startY) * t);

                    mw.setBounds(currentX, currentY, currentSize, currentSize);
                    mw.validate();
                }
                centerPanel.repaint();
            }
        });
        timer.start();
    }

    // Helper to calculate the South-East position
    private static void updateWindowBounds(MiniWindow mw, JPanel parent, int size) {
        int x = parent.getWidth() - size - 30;  // 30px from right
        int y = parent.getHeight() - size - 80; // 80px from bottom (clears taskbar)
        mw.setBounds(x, y, size, size);
        mw.validate(); // Forces the internal title bar to draw correctly
    }

    /*
    Include an isOpen boolean property for the window
    if isOpen, add to task bar item
    Unless 'x' is clicked, task bar is not terminated
    Minimed and not minimized statuses changed taskBar items
    Adding start button from footer as layout
     */
}