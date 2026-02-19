package layout.design;

import layout.constants.HangFonts;
import layout.constants.LoadTahoma;
import layout.constants.HangImages;
import layout.constants.MiniWindow; // Ensure this is imported

import javax.swing.*;
import javax.swing.border.LineBorder;
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
        JButton licenseButton = addLicenseButton("License", HangImages.licenseIcon, centerPanel);
        gbc.gridy = 0;
        menuButtons.add(licenseButton, gbc);

        // 2. MIDDLE-TOP (Devs)
        JButton adButton = addDevsButton("The Devs", HangImages.devsIcon, centerPanel);
        gbc.gridy = 1;
        menuButtons.add(adButton, gbc);

        // 3. MIDDLE-BOTTOM (How to Play)
        JButton htpButton = addHTPButton("How To Play", HangImages.htpIcon, centerPanel);
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

    // addCustomButton Method here
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

        // Define the event when clicked
        customButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // Define a panel to put the instructions inside
                JPanel inWindowPanel = new JPanel(new BorderLayout());
                inWindowPanel.setBackground(new Color(243, 241, 230));

                // Create a main content panel with vertical stacking
                JPanel contentPanel = new JPanel();
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                contentPanel.setOpaque(false);
                contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 15, 30));

                // This creates the header text
                JLabel headerLabel = new JLabel("You're trying to download a hidden file:");
                headerLabel.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN,17));
                headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // This creates the file box
                JPanel fileBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
                fileBox.setOpaque(false);
                fileBox.setAlignmentX(Component.CENTER_ALIGNMENT);

                // This creates the blue rounded line border
                fileBox.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(65, 120, 220), 3, true),
                        BorderFactory.createEmptyBorder(12, 40, 0, 40)
                ));

                // This creates the JLabel
                JLabel fileLabel = new JLabel("word.exe");
                fileLabel.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, 17));

                // Import the folder icon
                Image folderIcon = Toolkit.getDefaultToolkit().getImage("././resources/images/folder.png");
                Image scaledFolder = folderIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

                // Set icon and add text to the filebox
                fileLabel.setIcon(new ImageIcon(scaledFolder));
                fileBox.add(fileLabel);

                // Creates the string instructions
                // We use HTML here to handle the bullets and text wrapping
                // Note: We can adjust the width here if we edited the TARGET_SIZE below
                String rulesHtml = "<html><body style='width: 240px;'>" +
                        "<ul style='margin-left: 20px; padding-left: 0;'>" +
                        "<li style='margin-bottom: 5px;'>Guess the word one letter at a time.</li>" +
                        "<li style='margin-bottom: 5px;'>For every correct guess, the letter is revealed and your download progresses.</li>" +
                        "<li style='margin-bottom: 5px;'>For every wrong guess, system error popup appears and your computer takes damage.</li>" +
                        "<li style='margin-bottom: 5px;'>You only have 6 mistakes.</li>" +
                        "<li style='margin-bottom: 5px;'>If you reach 6 wrong guesses, your computer shuts down. Download failed.</li>" +
                        "<li>Reveal the full word before your system crashes to win!</li>" +
                        "</ul></body></html>";

                // Create a label using the HTML text above
                JLabel rulesLabel = new JLabel(rulesHtml);
                rulesLabel.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, 17));
                rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Combine everything
                contentPanel.add(headerLabel);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                contentPanel.add(fileBox);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                contentPanel.add(rulesLabel);
                inWindowPanel.add(contentPanel, BorderLayout.CENTER);

                // Trigger the animation
                animateMiniWindow(centerPanel, buttonName, inWindowPanel, customButton);
            }
        });

        return customButton;
    }

    // This creates the HTP window design and returns it as a button
    public static JButton addHTPButton(String buttonName, Image img, JPanel centerPanel) {

        // Create the button
        JButton htpButton = new JButton(buttonName);

        // Change size of button icons here
        int scale = (int)(HangFonts.titleFontSize * 1.5);
        Image scaledIcon = img.getScaledInstance(scale, scale, Image.SCALE_SMOOTH);
        htpButton.setIcon(new ImageIcon(scaledIcon));

        // Add more customization to the button
        htpButton.setHorizontalTextPosition(JButton.CENTER);
        htpButton.setVerticalTextPosition(JButton.BOTTOM);
        htpButton.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, HangFonts.regularFontSize));
        htpButton.setForeground(Color.WHITE);
        htpButton.setContentAreaFilled(false);
        htpButton.setBorderPainted(false);
        htpButton.setFocusPainted(false);

        // Define the event when clicked
        htpButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // Define a panel to put the instructions inside
                JPanel inWindowPanel = new JPanel(new BorderLayout());
                inWindowPanel.setBackground(new Color(243, 241, 230));

                // Create a main content panel with vertical stacking
                JPanel contentPanel = new JPanel();
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                contentPanel.setOpaque(false);
                contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 15, 30));

                // This creates the header text
                JLabel headerLabel = new JLabel("You're trying to download a hidden file:");
                headerLabel.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN,17));
                headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // This creates the file box
                JPanel fileBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
                fileBox.setOpaque(false);
                fileBox.setAlignmentX(Component.CENTER_ALIGNMENT);

                // This creates the blue rounded line border
                fileBox.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(65, 120, 220), 3, true),
                        BorderFactory.createEmptyBorder(12, 40, 0, 40)
                ));

                // This creates the JLabel
                JLabel fileLabel = new JLabel("word.exe");
                fileLabel.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, 17));

                // Import the folder icon
                Image folderIcon = Toolkit.getDefaultToolkit().getImage("././resources/images/folder.png");
                Image scaledFolder = folderIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH);

                // Set icon and add text to the filebox
                fileLabel.setIcon(new ImageIcon(scaledFolder));
                fileBox.add(fileLabel);

                // Creates the string instructions
                // We use HTML here to handle the bullets and text wrapping
                // Note: We can adjust the width here if we edited the TARGET_SIZE below
                String rulesHtml = "<html><body style='width: 240px;'>" +
                        "<ul style='margin-left: 20px; padding-left: 0;'>" +
                        "<li style='margin-bottom: 5px;'>Guess the word one letter at a time.</li>" +
                        "<li style='margin-bottom: 5px;'>For every correct guess, the letter is revealed and your download progresses.</li>" +
                        "<li style='margin-bottom: 5px;'>For every wrong guess, system error popup appears and your computer takes damage.</li>" +
                        "<li style='margin-bottom: 5px;'>You only have 6 mistakes.</li>" +
                        "<li style='margin-bottom: 5px;'>If you reach 6 wrong guesses, your computer shuts down. Download failed.</li>" +
                        "<li>Reveal the full word before your system crashes to win!</li>" +
                        "</ul></body></html>";

                // Create a label using the HTML text above
                JLabel rulesLabel = new JLabel(rulesHtml);
                rulesLabel.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, 17));
                rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Combine everything
                contentPanel.add(headerLabel);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                contentPanel.add(fileBox);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
                contentPanel.add(rulesLabel);
                inWindowPanel.add(contentPanel, BorderLayout.CENTER);

        // Define a fixed square size for ALL icons
        int iconSize = (int)(HangFonts.titleFontSize * 1.5);

        // Force the image into a square iconSize x iconSize
        Image scaledIcon = img.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        htpButton.setIcon(new ImageIcon(scaledIcon));
                // Trigger the animation
                animateMiniWindow(centerPanel, buttonName, inWindowPanel, htpButton);
            }
        });

        return htpButton;
    }

    // This creates the License window and returns it as a button like the ones above
    public static JButton addLicenseButton(String buttonName, Image img, JPanel centerPanel) {

        // Create the button
        JButton licButton = new JButton(buttonName);

        // Change size of button icons here
        int scale = (int)(HangFonts.titleFontSize * 1.5);
        Image scaledIcon = img.getScaledInstance(scale, scale, Image.SCALE_SMOOTH);
        licButton.setIcon(new ImageIcon(scaledIcon));

        // Ensure the button itself doesn't shrink-wrap too tightly
        licButton.setPreferredSize(new Dimension(scale + 40, scale + 40));

        licButton.setHorizontalTextPosition(JButton.CENTER);
        licButton.setVerticalTextPosition(JButton.BOTTOM);
        licButton.setIconTextGap(5);

        licButton.setFont(HangFonts.loadCustomFonts(Font.PLAIN, HangFonts.regularFontSize));
        licButton.setForeground(Color.WHITE);
        // Add more customization to the button
        licButton.setHorizontalTextPosition(JButton.CENTER);
        licButton.setVerticalTextPosition(JButton.BOTTOM);
        licButton.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, HangFonts.regularFontSize));
        licButton.setForeground(Color.WHITE);
        licButton.setContentAreaFilled(false);
        licButton.setBorderPainted(false);
        licButton.setFocusPainted(false);

        // Define the event when clicked
        licButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // This creates the panel to put the license text inside
                JPanel inWindowPanel = new JPanel(new BorderLayout());
                inWindowPanel.setBackground(new Color(243, 241, 230));

                // This naman creates a main content panel
                JPanel contentPanel = new JPanel();
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                contentPanel.setOpaque(false);
                contentPanel.setBorder(BorderFactory.createEmptyBorder(35, 30, 35, 30));

                // This creates the title box
                JPanel titleBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
                titleBox.setOpaque(false);
                titleBox.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Create the blue rounded line border with padding
                titleBox.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(65, 120, 220), 2, true), // The blue border
                        BorderFactory.createEmptyBorder(50, 30, 10, 30)   // Inner padding (Top, Left, Bottom, Right)
                ));

                // The label with the title
                JLabel titleLabel = new JLabel("CMSC 125 - HangMan OS Game");
                titleLabel.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, 17));
                titleBox.add(titleLabel);

                // Creates the string license text
                // We use HTML here to handle the bullets and text wrapping
                // Note again: We can adjust the width here if we edited the TARGET_SIZE below
                String licHtml = "<html><body style='width: 240px;'>" +
                        "<ul style='margin-left: 20px; padding-left: 0;'>" +
                        "<li style='margin-bottom: 8px;'>This game was developed for academic purposes.</li>" +
                        "<li style='margin-bottom: 8px;'>Free to use and modify for learning and non-commercial use only.</li>" +
                        "<li>Distribution or commercial use without permission is not allowed.</li>" +
                        "</ul></body></html>";

                JLabel licLabel = new JLabel(licHtml);
                licLabel.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, 17));;
                licLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Combine
                contentPanel.add(titleBox);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                contentPanel.add(licLabel);
                inWindowPanel.add(contentPanel, BorderLayout.CENTER);

                // Trigger  animation
                animateMiniWindow(centerPanel, buttonName, inWindowPanel, licButton);
            }
        });

        return licButton;
    }

    // This creates the License window and returns it as a button like the ones above
    public static JButton addDevsButton(String buttonName, Image img, JPanel centerPanel) {

        // Create the button
        JButton devsButton = new JButton(buttonName);

        // Change size of button icons here
        int scale = (int)(HangFonts.titleFontSize * 1.5);
        Image scaledIcon = img.getScaledInstance(scale, scale, Image.SCALE_SMOOTH);
        devsButton.setIcon(new ImageIcon(scaledIcon));

        // Add more customization to the button
        devsButton.setHorizontalTextPosition(JButton.CENTER);
        devsButton.setVerticalTextPosition(JButton.BOTTOM);
        devsButton.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, HangFonts.regularFontSize));
        devsButton.setForeground(Color.WHITE);
        devsButton.setContentAreaFilled(false);
        devsButton.setBorderPainted(false);
        devsButton.setFocusPainted(false);

        // Define the event when clicked
        devsButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // This creates the panel to put the license text inside
                JPanel inWindowPanel = new JPanel(new BorderLayout());
                inWindowPanel.setBackground(new Color(243, 241, 230));

                // This naman creates a main content panel
                JPanel contentPanel = new JPanel();
                contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
                contentPanel.setOpaque(false);
                contentPanel.setBorder(BorderFactory.createEmptyBorder(35, 30, 35, 30));

                // This creates the title box
                JPanel titleBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
                titleBox.setOpaque(false);
                titleBox.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Create the blue rounded line border with padding
                titleBox.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(65, 120, 220), 2, true), // The blue border
                        BorderFactory.createEmptyBorder(50, 30, 10, 30)   // Inner padding (Top, Left, Bottom, Right)
                ));

                // The label with the title
                JLabel titleLabel = new JLabel("The Developers");
                titleLabel.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, 17));
                titleBox.add(titleLabel);

                // Creates the string license text
                // We use HTML here to handle the bullets and text wrapping
                // Note again: We can adjust the width here if we edited the TARGET_SIZE below
                String devsHtml = "<html><body style='width: 240px;'>" +
                        "<ul style='margin-left: 20px; padding-left: 0;'>" +
                        "<li style='margin-bottom: 8px;'>Angela Almazan</li>" +
                        "<li style='margin-bottom: 8px;'>Mac Alvarico</li>" +
                        "<li style='margin-bottom: 8px;'>Desirre Barbosa</li>" +
                        "<li style='margin-bottom: 8px;'>Zsyvette Bugho</li>" +
                        "</ul></body></html>";

                JLabel devsLabel = new JLabel(devsHtml);
                devsLabel.setFont(LoadTahoma.loadCustomFonts(Font.PLAIN, 17));;
                devsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

                // Combine
                contentPanel.add(titleBox);
                contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
                contentPanel.add(devsLabel);
                inWindowPanel.add(contentPanel, BorderLayout.CENTER);

                // Trigger  animation
                animateMiniWindow(centerPanel, buttonName, inWindowPanel, devsButton);
            }
        });

        return devsButton;
    }

    private static void animateMiniWindow(JPanel centerPanel, String title, JPanel inWindowPanel, JButton sourceButton) {
        final int TARGET_SIZE = 370;

        // Clean up existing popups
        for (Component c : centerPanel.getComponents()) {
            if (c.getName() != null && c.getName().equals("PopupLayer")) {
                centerPanel.remove(c);
            }
        }

        // Set up the Popup Layer
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