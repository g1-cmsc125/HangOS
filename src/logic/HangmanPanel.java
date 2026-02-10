package logic;

import layout.constants.HangColors;
import layout.constants.HangFonts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class HangmanPanel extends JPanel {
    // Some changes - instead of adding a "wordPanel" directly put things in "hangmanPanel" as the upper box
    // where text resides
    public HangmanPanel() {
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.setBackground(HangColors.hangOSWordsPanel);
    }

    public void displayWord(ArrayList<Character> wordState){
        this.removeAll();
        for (char letter : wordState) {
            JLabel tile = createTile(letter);
            this.add(tile);
        }
        this.revalidate();
        this.repaint();
    }

    private JLabel createTile(char letter){
        JLabel label = new JLabel();

        int tileSize = 40;
        label.setPreferredSize(new Dimension(tileSize, tileSize));
        label.setHorizontalAlignment(SwingConstants.CENTER); label.setOpaque(true);
        label.setFont(HangFonts.loadCustomFonts(Font.PLAIN, 13)); // 13 for both this and virtual keyboard

        Border shadowBorder = BorderFactory.createMatteBorder(1,1,4,3, new Color(0, 0, 0, 50));
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        label.setBorder(BorderFactory.createCompoundBorder(shadowBorder, lineBorder));

        if (letter == '_') {
            // Empty guess slot
            label.setBackground(Color.WHITE);
            label.setText("");
        }
        else if (letter == ' ') {
            // Space between words (Invisible tile)
            label.setBackground(new Color(0xC9DCF5));
            label.setBorder(null); // No border for spaces
            label.setText("");
            label.setPreferredSize(new Dimension(20, 60)); // Narrower
        }
        else {
            // Correctly guessed letter
            label.setBackground(new Color(0x0844C3));
            label.setForeground(Color.WHITE);
            label.setText(String.valueOf(letter).toUpperCase());
            shadowBorder = BorderFactory.createMatteBorder(1,1,4,3, new Color(0x3980F4));
            label.setBorder(BorderFactory.createCompoundBorder(shadowBorder, lineBorder));
        }
        return label;
    }
}


