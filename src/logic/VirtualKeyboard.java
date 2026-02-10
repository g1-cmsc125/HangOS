package logic;

import layout.constants.HangColors;
import layout.constants.HangFonts;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VirtualKeyboard extends JPanel {
    ArrayList<JButton> buttonList = new ArrayList<>();
    public VirtualKeyboard(GameLogic gameLogic) {
        this.setLayout(new GridLayout(4, 1, 0, 3));
        this.setBackground(HangColors.keyboardPanel);
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Keyboard config, how many buttons per row index
        int[] rowConfig = {6, 7, 6, 7};
        int letterIndex = 0;

        for (int count : rowConfig) {
            JPanel rowPanel = new JPanel(); // matrix maker for keyboard layout
            rowPanel.setLayout(new GridLayout(1, count, 3, 0));
            rowPanel.setOpaque(false);

            for (int i = 0; i < count; i++) {
                String[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
                if (letterIndex < letters.length) {
                    String letter = letters[letterIndex];
                    JButton key = createRetroButton(letter);

                    // Review action listener
                    key.addActionListener(e -> {
                        // Review gameLogic handle guess method
                        gameLogic.handleGuess(letter);
                        key.setEnabled(false);
                        key.setBorder(BorderFactory.createLoweredBevelBorder());
                    });

                    buttonList.add(key);
                    rowPanel.add(key);
                    letterIndex++;
                }
            }
            this.add(rowPanel);
        }
    }

    private JButton createRetroButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(HangFonts.loadCustomFonts(Font.PLAIN, 13));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createRaisedBevelBorder());
        return btn;
    }

    // This method does not get called
    /*
    public void resetKeyboard() {
        for (JButton btn : buttonList) {
            btn.setEnabled(true);
            btn.setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }
     */
}