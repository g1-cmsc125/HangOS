package layout.design.designStartElements;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import layout.constants.HangCustomTahoma;
import layout.constants.RoundedGradientProgressBar;
import layout.constants.RoundedTile;

// This class creates the guesses (top part) of the game
public class HangmanPanel extends JPanel {
    private Timer animationTimer; // Class-level variable
    private final JPanel tileContainer; // Wrapper to hold the tiles together
    private final JProgressBar progressBar; // For progress rectangle text on the top
    private final JLabel statusLabel; // The label that says "Initializing..."
    private final Color xpBackgroundBlue = new Color(42, 115, 200);

    public HangmanPanel() {
        // 1. Configure the layout of the panel
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBackground(xpBackgroundBlue);
        this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        // 2. Initialize the custom progress bar (see RoundedGradientProgressBar on layout/constants)
        progressBar = new RoundedGradientProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(800, 40));
        progressBar.setBackground(new Color(0xf6f6f3));

        // 3. Define a custom font with adjustable spacing in between characters
        Font baseFont = HangCustomTahoma.loadCustomFonts(Font.BOLD, 20);
        Map<TextAttribute, Object> attributes = new HashMap<>();
        attributes.put(TextAttribute.TRACKING, 0.08);

        // 4. Set up status text and add the custom font settings
        statusLabel = new JLabel("Initializing secure download...");
        statusLabel.setFont(baseFont.deriveFont(attributes));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(15,0,20, 0));

        // 5. Create a container for the tiles using FlowLayout
        tileContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));
        tileContainer.setOpaque(false);
        tileContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 6. Fix dimension of the container because flow layout tends to shift size depending on state
        // 6. Increase the height from 50 to 150 to allow up to 3 rows of tiles
        Dimension preferredSize = new Dimension(800, 200); 
        Dimension minimumSize = new Dimension(800, 50);

        tileContainer.setPreferredSize(preferredSize);
        tileContainer.setMinimumSize(minimumSize);
        tileContainer.setMaximumSize(preferredSize); // Let it grow up to 150px tall
        // 7. Add progressBar, statusLabel and the tileContainer to the panel
        this.add(progressBar);
        this.add(statusLabel);
        this.add(tileContainer);
    }

    // This method updates the progress bar
    // Note: The calculation for correct and total are on GameLogic
    // public void updateProgress(int correct, int total) {
    //  // Stop any currently running animation before starting a new one
    //     if (animationTimer != null && animationTimer.isRunning()) {
    //         animationTimer.stop();
    //     }

    //     int target = (total <= 0) ? 0 : (int) (((double) correct / total) * 100);

    //     animationTimer = new Timer(30, e -> {
    //         int current = progressBar.getValue();

    //         if (current < target) {
    //             progressBar.setValue(current + 1);
    //         } else if (current > target) {
    //             progressBar.setValue(current - 1);
    //         } else {
    //             animationTimer.stop();
    //             if (target == 100) {
    //                 statusLabel.setText("Word Complete! Secure download finished.");
    //             }
    //         }
    //     });
    //     animationTimer.start();
    // }

    // This method updates the progress bar instantly based on string length
    public void updateProgress(int correct, int total) {
        
        // Set the maximum size of the progress bar to the length of the word
        progressBar.setMaximum(total);
        
        // Fill the progress bar based on exactly how many characters are revealed
        progressBar.setValue(correct);

        // Update the status label if the download is complete
        if (correct == total && total > 0) {
            statusLabel.setText("Word Complete! Secure download finished.");
        }
    }
    // This displays the word guesses
    public void displayWord(ArrayList<Character> wordState) {
    tileContainer.removeAll();

    // 1. Create a panel to hold the current word we are building
    JPanel currentWordPanel = createNewWordPanel();

    for (char letter : wordState) {
        if (letter == ' ') {
            // 2. We hit a space! Add the completed word panel to the container
            tileContainer.add(currentWordPanel);
            
            // 3. Start a brand new panel for the next word
            currentWordPanel = createNewWordPanel();
        } else {
            // 4. Add the letter tile to the current word's panel
            JLabel tile = createTile(letter);
            currentWordPanel.add(tile);
        }
    }
    
    // 5. Add the very last word panel
    tileContainer.add(currentWordPanel);

    tileContainer.revalidate();
    tileContainer.repaint();
    }

    // Helper method to keep word panels consistent
    private JPanel createNewWordPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel.setOpaque(false); // Transparent to show blue background
        return panel;
    }

    // This creates the tiles in the word guesses
    private JLabel createTile(char letter) {

        // Customize the size
        int tileSize = 45;
        // Create the customizable rounded tile (See RoundedTile on layout/constants)
        RoundedTile tile = new RoundedTile(letter, tileSize);

        // Font Handling
        try {
            tile.setFont(HangCustomTahoma.loadCustomFonts(Font.BOLD, 22));
        } catch (Exception e) {
            tile.setFont(new Font("SansSerif", Font.BOLD, 20));
        }

        // Initial State Logic
        if (letter == '_') {
            tile.setGuessed(false);
            tile.setText("");
        } else if (letter != ' ') {
            // If the letter is already known/guessed at creation
            tile.setGuessed(true);
        }

        return tile;
    }

}