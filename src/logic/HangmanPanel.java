package logic;

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
    JPanel wordPanel;

    public HangmanPanel() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(400, 300));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Temporary border to see it
        this.setVisible(true);
        GridBagConstraints gbc = new GridBagConstraints();

        wordPanel = new JPanel();
        wordPanel.setBackground(new Color(0xC9DCF5));
        wordPanel.setLayout(new FlowLayout());
        wordPanel.setPreferredSize(new Dimension(400, 300));

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(wordPanel, gbc);
    }

    public void displayWord(ArrayList<Character> wordstate){
        wordPanel.removeAll();

        for (char letter : wordstate) {
            //JLabel displayLetter = new JLabel(String.valueOf(letter));
            //displayLetter.setFont(new Font("Arial", Font.BOLD, 30));
            JLabel tile = createTile(letter);
            wordPanel.add(tile);
        }
        wordPanel.revalidate();
        wordPanel.repaint();
    }

    private JLabel createTile(char letter){
        JLabel label = new JLabel();

        label.setPreferredSize(new Dimension(40, 40));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);
        label.setFont(new Font("Tahoma", Font.TRUETYPE_FONT, 11));

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

    @Override
    protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
        }
    }


