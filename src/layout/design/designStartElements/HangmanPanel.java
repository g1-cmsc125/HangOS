package layout.design.designStartElements;

import layout.constants.HangColors;
import layout.constants.HangFonts;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;

public class HangmanPanel extends JPanel {

    private JPanel tileContainer; // Wrapper to hold the tiles together

    public HangmanPanel() {
        // 1. Use GridBagLayout on the main panel to center the container
        this.setLayout(new GridBagLayout());
        this.setBackground(HangColors.hangOSWordsPanel);

        // 2. Create a container for the tiles using FlowLayout
        tileContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        tileContainer.setOpaque(false); // Make it transparent to show background

        // 3. Add the container to the center of the main panel
        this.add(tileContainer, new GridBagConstraints());
    }

    public void displayWord(ArrayList<Character> wordState){
        // 4. Clear the container, NOT the main panel
        tileContainer.removeAll();

        for (char letter : wordState) {
            JLabel tile = createTile(letter);
            tileContainer.add(tile);
        }

        // 5. Refresh the UI
        tileContainer.revalidate();
        tileContainer.repaint();
    }

    private JLabel createTile(char letter){
        JLabel label = new JLabel();

        // Increased size slightly to look better centered
        int tileSize = 50;
        label.setPreferredSize(new Dimension(tileSize, tileSize));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setOpaque(true);

        // Load font (ensure your Font loader is working, otherwise use standard Font)
        try {
            label.setFont(HangFonts.loadCustomFonts(Font.BOLD, 20));
        } catch (Exception e) {
            label.setFont(new Font("Arial", Font.BOLD, 20));
        }

        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);

        // Shadow effect logic
        if (letter == '_') {
            label.setBackground(Color.WHITE);
            label.setText("");
            Border shadowBorder = BorderFactory.createMatteBorder(0,0,4,0, new Color(0, 0, 0, 50));
            label.setBorder(BorderFactory.createCompoundBorder(shadowBorder, lineBorder));
        }
        else if (letter == ' ') {
            label.setBackground(HangColors.hangOSWordsPanel); // Match background
            label.setBorder(null);
            label.setText("");
            label.setPreferredSize(new Dimension(20, 50));
        }
        else {
            label.setBackground(new Color(0x0844C3));
            label.setForeground(Color.WHITE);
            label.setText(String.valueOf(letter).toUpperCase());

            Border shadowBorder = BorderFactory.createMatteBorder(0,0,4,0, new Color(0x052d82));
            label.setBorder(BorderFactory.createCompoundBorder(shadowBorder, lineBorder));
        }
        return label;
    }
}