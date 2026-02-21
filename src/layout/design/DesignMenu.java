package layout.design;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
import layout.Card;
import layout.constants.HangCustomTahoma;
import layout.constants.HangFonts;
import layout.constants.HangImages;
import layout.constants.MiniWindow;
import layout.constants.SoundManager;

public class DesignMenu {

    public static void displayMenuButtons(JPanel centerPanel) {
        centerPanel.setLayout(new BorderLayout());

        JPanel menuButtons = new JPanel(new GridBagLayout());
        menuButtons.setOpaque(false);
        menuButtons.setBorder(BorderFactory.createEmptyBorder(0, 10, 20, 0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, HangFonts.regularFontSize, 0);

        // 1. License
        gbc.gridy = 0;
        menuButtons.add(addCustomButton("License", HangImages.licenseIcon, centerPanel), gbc);

        // 2. The Devs
        gbc.gridy = 1;
        menuButtons.add(addCustomButton("The Devs", HangImages.devsIcon, centerPanel), gbc);

        // 3. How To Play
        gbc.gridy = 2;
        menuButtons.add(addCustomButton("How To Play", HangImages.htpIcon, centerPanel), gbc);

        // 4. Start
        gbc.gridy = 3;
        menuButtons.add(addCustomButton("Start", HangImages.startIcon, centerPanel), gbc);

        JPanel southWrapper = new JPanel(new BorderLayout());
        southWrapper.setOpaque(false);
        southWrapper.add(menuButtons, BorderLayout.WEST);

        centerPanel.add(southWrapper, BorderLayout.SOUTH);
    }

    public static JButton addCustomButton(String buttonName, Image img, JPanel centerPanel) {
        JButton customButton = new JButton(buttonName);
        int iconSize = (int) (HangFonts.titleFontSize * 1.5);

        Image scaledIcon = img.getScaledInstance(iconSize, iconSize, Image.SCALE_SMOOTH);
        customButton.setIcon(new ImageIcon(scaledIcon));
        customButton.setPreferredSize(new Dimension(iconSize + 40, iconSize + 40));
        customButton.setHorizontalTextPosition(JButton.CENTER);
        customButton.setVerticalTextPosition(JButton.BOTTOM);
        customButton.setIconTextGap(5);
        customButton.setFont(HangCustomTahoma.loadCustomFonts(Font.PLAIN, HangFonts.regularFontSize));
        customButton.setForeground(Color.WHITE);
        customButton.setContentAreaFilled(false);
        customButton.setBorderPainted(false);
        customButton.setFocusPainted(false);

        customButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                switch (buttonName) {
                    case "Start":
                        SoundManager.playSystemSound("Windows Navigation Start.wav");
                        Card.screenChoice("Start");
                        break;
                    case "How To Play":
                        showHTPWindow(customButton, centerPanel);
                        break;
                    case "License":
                    case "The Devs":
                        showStandardWindow(buttonName, customButton, centerPanel);
                        break;
                }
            }
        });

        return customButton;
    }

    // --- SEPARATE METHOD FOR HOW TO PLAY (Preserves the unique design) ---
    private static void showHTPWindow(JButton btn, JPanel centerPanel) {
        SoundManager.playSystemSound("Windows XP Balloon.wav");

        JPanel inWindowPanel = new JPanel(new BorderLayout());
        inWindowPanel.setBackground(new Color(243, 241, 230));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(25, 30, 15, 30));

        JLabel headerLabel = new JLabel("You're trying to download a hidden file:");
        headerLabel.setFont(HangCustomTahoma.loadCustomFonts(Font.PLAIN, 17));
        headerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel fileBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        fileBox.setOpaque(false);
        fileBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        fileBox.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(65, 120, 220), 3, true),
                BorderFactory.createEmptyBorder(12, 40, 0, 40)
        ));

        JLabel fileLabel = new JLabel("word.exe");
        fileLabel.setFont(HangCustomTahoma.loadCustomFonts(Font.PLAIN, 17));
        Image folderIcon = Toolkit.getDefaultToolkit().getImage("././resources/images/folder.png");
        fileLabel.setIcon(new ImageIcon(folderIcon.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
        fileBox.add(fileLabel);

        String rulesHtml = "<html><body style='width: 240px;'><ul style='margin-left: 20px; padding-left: 0;'>" +
                "<li style='margin-bottom: 5px;'>Guess the word one letter at a time.</li>" +
                "<li style='margin-bottom: 5px;'>For every correct guess, the letter is revealed and your download progresses.</li>" +
                "<li style='margin-bottom: 5px;'>For every wrong guess, system error popup appears and your computer takes damage.</li>" +
                "<li style='margin-bottom: 5px;'>You only have 6 mistakes.</li>" +
                "<li style='margin-bottom: 5px;'>If you reach 6 wrong guesses, your computer shuts down. Download failed.</li>" +
                "<li>Reveal the full word before your system crashes to win!</li>" +
                "</ul></body></html>";

        JLabel rulesLabel = new JLabel(rulesHtml);
        rulesLabel.setFont(HangCustomTahoma.loadCustomFonts(Font.PLAIN, 17));
        rulesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(headerLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(fileBox);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        contentPanel.add(rulesLabel);
        inWindowPanel.add(contentPanel, BorderLayout.CENTER);

        animateMiniWindow(centerPanel, btn.getText(), inWindowPanel, btn);
    }

    // --- OPTIMIZED METHOD FOR LICENSE & DEVS (Consolidated UI) ---
    private static void showStandardWindow(String type, JButton btn, JPanel centerPanel) {
        SoundManager.playSystemSound("Windows XP Balloon.wav");

        JPanel inWindowPanel = new JPanel(new BorderLayout());
        inWindowPanel.setBackground(new Color(243, 241, 230));

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setOpaque(false);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(35, 30, 35, 30));

        JPanel titleBox = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        titleBox.setOpaque(false);
        titleBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleBox.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(65, 120, 220), 2, true),
                BorderFactory.createEmptyBorder(50, 30, 10, 30)
        ));

        String title = type.equals("License") ? "CMSC 125 - HangMan OS Game" : "The Developers";
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(HangCustomTahoma.loadCustomFonts(Font.PLAIN, 17));
        titleBox.add(titleLabel);

        String bodyHtml = type.equals("License") ?
                "<html><body style='width: 240px;'><ul style='margin-left: 20px;'>" +
                        "<li style='margin-bottom: 8px;'>This game was developed for academic purposes.</li>" +
                        "<li style='margin-bottom: 8px;'>Free to use and modify for learning and non-commercial use only.</li>" +
                        "<li>Distribution or commercial use without permission is not allowed.</li></ul></body></html>" :
                "<html><body style='width: 240px;'><ul style='margin-left: 20px;'>" +
                        "<li style='margin-bottom: 8px;'>Angela Almazan</li>" +
                        "<li style='margin-bottom: 8px;'>Mac Alvarico</li>" +
                        "<li style='margin-bottom: 8px;'>Desirre Barbosa</li>" +
                        "<li style='margin-bottom: 8px;'>Zsyvette Bugho</li></ul></body></html>";

        JLabel bodyLabel = new JLabel(bodyHtml);
        bodyLabel.setFont(HangCustomTahoma.loadCustomFonts(Font.PLAIN, 17));
        bodyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(titleBox);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        contentPanel.add(bodyLabel);
        inWindowPanel.add(contentPanel, BorderLayout.CENTER);

        animateMiniWindow(centerPanel, btn.getText(), inWindowPanel, btn);
    }

    private static void animateMiniWindow(JPanel centerPanel, String title, JPanel inWindowPanel, JButton sourceButton) {
        final int TARGET_SIZE = 370;

        for (Component c : centerPanel.getComponents()) {
            if ("PopupLayer".equals(c.getName())) centerPanel.remove(c);
        }

        JPanel popupLayer = new JPanel(null);
        popupLayer.setName("PopupLayer");
        popupLayer.setOpaque(false);
        popupLayer.setBounds(0, 0, centerPanel.getWidth(), centerPanel.getHeight());

        Point startPoint = SwingUtilities.convertPoint(sourceButton, 0, 0, centerPanel);
        int endX = centerPanel.getWidth() - TARGET_SIZE - 30;
        int endY = centerPanel.getHeight() - TARGET_SIZE - 80;

        MiniWindow mw = new MiniWindow(title, 0, 0, inWindowPanel);
        popupLayer.add(mw);
        centerPanel.add(popupLayer);
        centerPanel.setComponentZOrder(popupLayer, 0);

        Timer timer = new Timer(15, null);
        timer.addActionListener(new ActionListener() {
            double t = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                t += 0.08;
                if (t >= 1.0) {
                    mw.setBounds(endX, endY, TARGET_SIZE, TARGET_SIZE);
                    mw.validate();
                    timer.stop();
                } else {
                    int currentSize = (int) (TARGET_SIZE * t);
                    int currentX = (int) (startPoint.x + (endX - startPoint.x) * t);
                    int currentY = (int) (startPoint.y + (endY - startPoint.y) * t);
                    mw.setBounds(currentX, currentY, currentSize, currentSize);
                    mw.validate();
                }
                centerPanel.repaint();
            }
        });
        timer.start();
    }
}