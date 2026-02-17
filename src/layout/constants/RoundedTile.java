package layout.constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

// This new class creates a customized design for the rounded tile for HangManPanel
// Rounded tile is the container of the letter guessed by the user
public class RoundedTile extends JLabel {

    private boolean isGuessed = false;
    private final boolean isSpace;

    // This initializes the tile with a container and dimension
    public RoundedTile(char letter, int size) {
        super("", SwingConstants.CENTER);

        // This treats the spaces as invisible so it wont show
        this.isSpace = (letter == ' ');
        setPreferredSize(new Dimension(isSpace ? 20 : size, size));
        setOpaque(false);

        // This makes the guesses to uppercase
        if (!isSpace) {
            setText(String.valueOf(letter).toUpperCase());
        }
    }

    // This updates the guess state
    public void setGuessed(boolean guessed) {
        this.isGuessed = guessed;
        repaint(); // Refresh the UI to show new colors
    }


    @Override
    protected void paintComponent(Graphics g) {

        // If space, don't draw anything at all
        if (isSpace) return;

        // Create the 2d stuff and use antialiasing for smooth rounded corners
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Logic for background and border colors
        Color bg = isGuessed ? new Color(0x356DE4) : Color.WHITE;
        Color border = isGuessed ? Color.WHITE : new Color(0xCCCCCC);
        setForeground(isGuessed ? Color.WHITE : Color.BLACK);

        // Draw Background
        g2.setColor(bg);
        int radius = 10;
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));

        // Draw Border
        g2.setColor(border);
        g2.setStroke(new BasicStroke(2f));
        g2.draw(new RoundRectangle2D.Float(1, 1, getWidth() - 2, getHeight() - 2, radius, radius));

        g2.dispose();
        super.paintComponent(g);
    }
}
