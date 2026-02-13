package layout.design.designStartElements;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import layout.constants.HangColors;
import layout.constants.HangFonts;

public class VirtualKeyboard extends JPanel {
    ArrayList<JButton> buttonList = new ArrayList<>();
    private final String[] row1 = "QWERTYUIOP".split("");
    private final String[] row2 = "ASDFGHJKL".split("");
    private final String[] row3 = "ZXCVBNM".split("");
    GameLogic gameLogic;
    public VirtualKeyboard(GameLogic gameLogic) {
        //this.setLayout(new GridLayout(4, 1, 0, 3));
        this.gameLogic = gameLogic;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create the three rows
        add(createRowPanel(row1, gameLogic));
        add(createRowPanel(row2, gameLogic));
        add(createRowPanel(row3, gameLogic));
        //this.setBackground(HangColors.keyboardPanel);
        this.setOpaque(true);
    }

    private JPanel createRowPanel(String[] keys, GameLogic logic) {
        // CHANGE 1: Set the last number (Vertical Gap) to 0
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        panel.setBackground(HangColors.hangOSWordsPanel);
        panel.setBorder(null); // Ensure no border padding exists

        for (String keyText : keys) {
            JButton btn = createRetroButton(keyText);
            btn.setPreferredSize(new Dimension(50, 65)); // Your button height is 80

            btn.addActionListener(e -> {
                gameLogic.handleGuess(keyText);
                btn.setEnabled(false);
                btn.setBorder(BorderFactory.createLoweredBevelBorder());
            });
            buttonList.add(btn);
            panel.add(btn);
        }

        // CHANGE 2: Prevent BoxLayout from stretching the row vertically.
        // We set the max height to 80 (matching your button height).
        // Integer.MAX_VALUE for width allows it to stretch horizontally if needed

        return panel;
    }

        // Keyboard config, how many buttons per row index
        /*int[] rowConfig = {6, 7, 6, 7};
        int letterIndex = 0;

        for (int count : rowConfig) {
            JPanel rowPanel = new JPanel(); // matrix maker for keyboard layout
            rowPanel.setLayout(new GridLayout(1, count, 3, 0));
            rowPanel.setOpaque(false);

            for (int i = 0; i < count; i++) {
                //String[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
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
    }*/

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
    public void resetKeyboard() {
        for (JButton btn : buttonList) {
            btn.setEnabled(true);
            btn.setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }
}