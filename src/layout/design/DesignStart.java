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
        inWindowPanel.setOpaque(true);
        inWindowPanel.setBackground(new Color(0xECE9D8));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Stretch component both horizontally and vertically
        gbc.weightx = 1.0;

        // Panels inside MiniWindow
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

        MiniWindow mw = new MiniWindow("Hangman", (int) (0.65 * Design.screenWidth), (int) (0.65 * Design.screenHeight), inWindowPanel);
        mw.setBackground(new Color(0xECE9D8));

        centerPanel.add(mw, BorderLayout.SOUTH);
    }

}
