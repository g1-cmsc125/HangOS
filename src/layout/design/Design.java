package layout.design;

import layout.Card;
import layout.constants.HangColors;
import layout.constants.HangFonts;
import layout.constants.StartButton;
import layout.constants.TaskBarItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Design {
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int screenWidth = screenSize.width;
    public static int screenHeight = screenSize.height;

    static int footerHeight = (int)(Design.screenHeight * 0.10);
    public static void centerDesignDefault(JPanel mainPanel, JPanel centerPanel) {
        centerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.90)));
        centerPanel.setOpaque(false);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }

    public static void footerDesign(JPanel mainPanel) {
        JPanel bottomPanel = new JPanel(new GridBagLayout());

        bottomPanel.setPreferredSize(new Dimension(Design.screenWidth, footerHeight));
        bottomPanel.setOpaque(true);
        bottomPanel.setBackground(new Color(31, 107, 229));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; gbc.weighty = 1.0; gbc.gridheight = 1;

        addStartButton(bottomPanel, gbc);
        addTaskBar(bottomPanel, gbc);
        addTimePanel(bottomPanel, gbc);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }

    private static void addStartButton(JPanel bottomPanel, GridBagConstraints gbc) {
        StartButton startButton = new StartButton();
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.05;

        startButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Card.screenChoice("Start");
            }
        });

        bottomPanel.add(startButton, gbc);
    }

    private static void addTaskBar(JPanel bottomPanel, GridBagConstraints gbc) {
        int btnHeight = (int)(footerHeight * 0.70);
        int vGap = (footerHeight - btnHeight) / 2;

        JPanel blueTaskbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, vGap));
        blueTaskbar.setOpaque(false);
        blueTaskbar.setBorder(BorderFactory.createEmptyBorder(0, 7, 0, 0));

        // Dynamically add taskBarItem dependent on opened/closed tabs
        /*
        for(int i = 0; i < 2; i++) {
            boolean isActive = (i == 1);
            TaskBarItem tbi = new TaskBarItem("Folder", isActive);
            tbi.setPreferredSize(new Dimension(150, btnHeight));
            blueTaskbar.add(tbi);
        }
        */

        gbc.gridx = 1; gbc.weightx = 0.80;
        bottomPanel.add(blueTaskbar, gbc);
    }

    private static void addTimePanel(JPanel bottomPanel, GridBagConstraints gbc) {
        JPanel timeContainer = new JPanel();
        // Using GridBagLayout inside the container for perfect centering
        timeContainer.setLayout(new GridBagLayout());
        timeContainer.setBackground(HangColors.timeContainer);

        // Keep the right padding so the text isn't touching the very edge of the screen
        timeContainer.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 7));

        gbc.gridx = 2;
        gbc.weightx = 0.05;
        gbc.fill = GridBagConstraints.BOTH; // Fill the height of the bottomPanel

        JLabel time = new JLabel("3:24 PM");
        time.setFont(HangFonts.loadCustomFonts(Font.PLAIN, HangFonts.subTitleFontSize));
        time.setForeground(Color.WHITE);

        // GridBagConstraints for the label to align it to the RIGHT
        GridBagConstraints labelGbc = new GridBagConstraints();
        labelGbc.weightx = 1.0;
        labelGbc.anchor = GridBagConstraints.EAST; // Anchors text to the right

        timeContainer.add(time, labelGbc);
        bottomPanel.add(timeContainer, gbc);
    }
}