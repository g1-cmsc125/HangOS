package layout.design;

import logic.*;
import layout.constants.MiniWindow;

import javax.swing.*;
import java.awt.*;

/* Add this layout */
public class DesignStart {

    public static void displayStartWindow(JPanel centerPanel) {
        // Set as grid bag layout -- removing additional wrappers/layout formats for CENTERING
        centerPanel.setLayout(new GridBagLayout());

        // Internal Setup
        JPanel inWindowPanel = new JPanel(new GridBagLayout());
        inWindowPanel.setOpaque(true);
        inWindowPanel.setBackground(new Color(0xECE9D8)); // set as constant color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // Check these panels for duplicates etc.
        CardPanel cardPanel = new CardPanel();
        HangmanPanel hangmanPanel = new HangmanPanel();
        GameLogic gameLogic = new GameLogic(cardPanel, hangmanPanel);
        VirtualKeyboard keyboard = new VirtualKeyboard(gameLogic);

        // GBC configs
        gbc.gridx = 0; gbc.gridy = 0; gbc.weighty = 0.7;
        inWindowPanel.add(hangmanPanel, gbc);

        gbc.gridy = 1; gbc.weighty = 0.3;
        inWindowPanel.add(keyboard, gbc);

        // MiniWindow for the HangOS screen & virtual keyboard
        MiniWindow mw = new MiniWindow("Hangman", (int) (0.65 * Design.screenWidth), (int) (0.65 * Design.screenHeight), inWindowPanel);
        centerPanel.add(mw, new GridBagConstraints()); // Auto centering w/ grid bag layout
    }
}
