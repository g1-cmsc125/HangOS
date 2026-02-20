package layout.design;

import layout.design.designStartElements.*;
import layout.constants.MiniWindow;

import javax.swing.*;
import java.awt.*;

/* Add this layout */
public class DesignStart {
    // Refer to these static values when resizing in the hangOS
    public static int hangOSWidth = (int) (0.65 * Design.screenWidth);
    public static int hangOSHeight = (int) (0.65 * Design.screenHeight);

    // Initialize these normally
    public static VirtualKeyboard keyboard = new VirtualKeyboard();
    public static HangmanPanel hangmanPanel = new HangmanPanel();
    public static GameLogic gameLogic = new GameLogic();

    // Create a static block to link them AFTER they are all created
    static {
        keyboard.setGameLogic(gameLogic);
        gameLogic.setupGame(hangmanPanel);
    }

    public static void displayHangOS(JPanel centerPanel) {
        // Set as grid bag layout -- removing additional wrappers/layout formats for CENTERING
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBackground(Color.black);

        // Internal Setup
        JPanel inWindowPanel = new JPanel(new GridBagLayout());
        inWindowPanel.setOpaque(true);
        inWindowPanel.setBackground(Color.black); // set as constant color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // GBC configs
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.4;
        inWindowPanel.add(hangmanPanel, gbc);

        gbc.gridy = 1;
        gbc.weightx = 0.9;
        gbc.weighty = 0.6;
        inWindowPanel.add(keyboard, gbc);

        // MiniWindow for the HangOS screen & virtual keyboard
        MiniWindow mw = new MiniWindow("Hangman", hangOSWidth, hangOSHeight, inWindowPanel);
        centerPanel.add(mw, new GridBagConstraints()); // Auto centering w/ grid bag layout
    }
}
