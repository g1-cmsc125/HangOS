package layout.design.designStartElements;

import layout.constants.HangCustomTahoma;
import layout.constants.HangFonts;
import layout.constants.RoundedGradientProgressBar;
import layout.constants.RoundedTile;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

// This class creates the guesses (top part) of the game
public class HangmanPanel extends JPanel {

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
        tileContainer = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        tileContainer.setOpaque(false);
        tileContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 6. Fix dimension of the container because flow layout tends to shift size depending on state
        Dimension fixedTileSize = new Dimension(800, 50);
        tileContainer.setPreferredSize(fixedTileSize);
        tileContainer.setMinimumSize(fixedTileSize);
        tileContainer.setMaximumSize(fixedTileSize);

        // 7. Add progressBar, statusLabel and the tileContainer to the panel
        this.add(progressBar);
        this.add(statusLabel);
        this.add(tileContainer);
    }

    // This method updates the progress bar
    // Note: The calculation for correct and total are on GameLogic
    public void updateProgress(int correct, int total) {

        // Create the timer
        Timer timer = null;
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        // Calculate percentage (handle division by zero)
        int target = (total <= 0) ? 0 : (int) (((double) correct / total) * 100);

        // Initialize the timer once, or reuse the logic
        timer = new Timer(30, e -> {
            int current = progressBar.getValue();

            if (current < target) {
                progressBar.setValue(current + 1);
            } else if (current > target) {
                progressBar.setValue(current - 1);
            } else {
                // Target reached
                ((Timer)e.getSource()).stop();
                if (target == 100) {
                    statusLabel.setText("Word Complete! Secure download finished.");
                }
            }
        });
        timer.start();
    }


    // This displays the word guesses
    public void displayWord(ArrayList<Character> wordState){
        // Clear the container, NOT the main panel
        tileContainer.removeAll();

        for (char letter : wordState) {
            JLabel tile = createTile(letter);
            tileContainer.add(tile);
        }

        // Refresh the UI
        tileContainer.revalidate();
        tileContainer.repaint();
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