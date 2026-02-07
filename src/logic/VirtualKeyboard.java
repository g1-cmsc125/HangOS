package logic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VirtualKeyboard extends JPanel {
    private String[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
    private ArrayList<JButton> buttonList = new ArrayList<>();

    public VirtualKeyboard(GameLogic gameLogic) {
        this.setLayout(new GridLayout(4, 1, 0, 3));

        this.setBackground(new Color(10, 60, 230));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        this.setPreferredSize(new Dimension(600, 220));

        // for the keyboard config
        int[] rowConfig = {6, 7, 6, 7};
        int letterIndex = 0;

        for (int count : rowConfig) {
            JPanel rowPanel = new JPanel();
            rowPanel.setLayout(new GridLayout(1, count, 3, 0));

            rowPanel.setOpaque(false);

            for (int i = 0; i < count; i++) {
                if (letterIndex < letters.length) {
                    String letter = letters[letterIndex];
                    JButton key = createRetroButton(letter);

                    key.addActionListener(e -> {
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
        btn.setFont(new Font("Tahoma", Font.TRUETYPE_FONT, 11));
        btn.setBackground(Color.WHITE);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createRaisedBevelBorder());
        return btn;
    }

    public void resetKeyboard() {
        for (JButton btn : buttonList) {
            btn.setEnabled(true);
            btn.setBorder(BorderFactory.createRaisedBevelBorder());
        }
    }
}