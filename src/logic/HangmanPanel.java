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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HangmanPanel extends JPanel {
    JPanel wordPanel;

    public HangmanPanel() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(400, 300));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Temporary border to see it
        this.setVisible(true);
        GridBagConstraints gbc = new GridBagConstraints();

        wordPanel = new JPanel();
        wordPanel.setBackground(Color.yellow);
        wordPanel.setLayout(new FlowLayout());
        wordPanel.setPreferredSize(new Dimension(400, 300));

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy= 0;
        this.add(wordPanel, gbc);
    }

    public void displayWord(ArrayList<Character> wordstate){
        wordPanel.removeAll();

        for (char letter : wordstate) {
            JLabel displayLetter = new JLabel(String.valueOf(letter));
            displayLetter.setFont(new Font("Arial", Font.BOLD, 30));
            wordPanel.add(displayLetter);
        }
        wordPanel.revalidate();
        wordPanel.repaint();
    }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

        }

    }


