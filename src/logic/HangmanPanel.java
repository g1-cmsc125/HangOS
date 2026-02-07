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
    HangmanDrawing figurePanel;


    public HangmanPanel() {
        this.setLayout(new GridBagLayout());

        this.setPreferredSize(new Dimension(400, 300));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Temporary border to see it
        this.setVisible(true);

        GridBagConstraints gbc = new GridBagConstraints();

        wordPanel = new JPanel();
        figurePanel= new HangmanDrawing();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy= 0;
        gbc.weightx = 1;
        gbc.weighty= .8;
        this.add(figurePanel, gbc);
        figurePanel.setBackground(Color.WHITE);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridy= 1;
        gbc.weightx = 1;
        gbc.weighty= .2;
        this.add(wordPanel, gbc);
        wordPanel.setBackground(Color.yellow);


        wordPanel.setLayout(new FlowLayout());


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

    public void refreshPanel(){
        figurePanel.revalidate();
        figurePanel.repaint();

    }

    class HangmanDrawing extends JPanel {
        int wrongGuesses = 0;
        public HangmanDrawing() {
            this.setBackground(Color.WHITE); // Make it stand out
        }

        public void setWrongGuesses(int wrongGuesses){
            this.wrongGuesses = wrongGuesses;
            figurePanel.removeAll();
            refreshPanel();

        }

        public void updateFigure(){//If we want to use an image instead of paint component

        }


        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            switch (wrongGuesses) {
                case 1:
                    g.setColor(Color.CYAN);
                    g.fillOval(50, 50, 100, 100);
                    break;
                case 2:
                    g.setColor(Color.YELLOW);
                    g.fillOval(50, 50, 100, 100);

                    break;
                case 3:
                    g.setColor(Color.ORANGE);
                    g.fillOval(50, 50, 100, 100);

                    break;
                case 4:
                    g.setColor(Color.PINK);
                    g.fillOval(50, 50, 100, 100);

                    break;
                case 5:
                    g.setColor(Color.RED);
                    g.fillOval(50, 50, 100, 100);


                    break;
                case 6:
                    g.setColor(Color.WHITE);
                    g.fillOval(50, 50, 100, 100);
                    break;

                default:
                    break;
            }
        }

    }
}

