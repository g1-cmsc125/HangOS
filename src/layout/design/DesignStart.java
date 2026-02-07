package layout.design;

import logic.*;
import layout.constants.MiniWindow;

import javax.swing.*;
import java.awt.*;

/* Add this layout */
public class DesignStart {


    public static void displayStartWindow(JPanel centerPanel) {
        centerPanel.setLayout(new BorderLayout());

        // Customizable JPanel inside MiniWindow
        JPanel inWindowPanel = new JPanel(new GridBagLayout());
        //JLabel test = new JLabel("This JPanel is customizable.", SwingConstants.CENTER);
        //test.setForeground(Color.BLACK);
        //inWindowPanel.add(test, BorderLayout.CENTER);

        inWindowPanel.setOpaque(true);
        inWindowPanel.setBackground(new Color(0xECE9D8));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Stretch component both horizontally and vertically
        gbc.weightx = 1.0;

        CardPanel cardPanel = new CardPanel();
        HangmanPanel hangmanPanel = new HangmanPanel();
        GameLogic gameLogic = new GameLogic(cardPanel, hangmanPanel);
        VirtualKeyboard keyboard = new VirtualKeyboard(gameLogic);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.7;
        inWindowPanel.add(hangmanPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        inWindowPanel.add(keyboard, gbc);

        MiniWindow mw = new MiniWindow("Hangman", 500, 350, inWindowPanel);
        mw.setBackground(new Color(0xECE9D8));

        // Wrapper for multi-dir-anchor purposes
        JPanel southWrapper = new JPanel();
        southWrapper.setLayout(new BorderLayout());
        southWrapper.add(mw, BorderLayout.SOUTH);
        southWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 35, 120));
        southWrapper.setOpaque(false);

        centerPanel.add(southWrapper, BorderLayout.EAST);
    }

}
