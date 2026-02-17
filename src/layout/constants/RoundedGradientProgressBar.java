package layout.constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

// This new class styles the progress bar used on HangManPanel
public class RoundedGradientProgressBar extends JProgressBar {

    // We can adjust this value for the rounded effect
    private final int arcSize = 12;

    // To create the progress bar with the minimum and maximum value of the JProgressBar
    public RoundedGradientProgressBar(int min, int max) {
        super(min, max);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Customize the progressbar
        int width = getWidth();
        int height = getHeight();

        // Draw background track of the progress bar
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width, height, arcSize, arcSize);

        double percent = getPercentComplete();
        int progressWidth = (int) (width * percent);

        // Only draw idf there's progress
        if (progressWidth > 0) {
            int inset = 4;
            int innerW = Math.max(0, progressWidth - (inset * 2));
            int innerH = height - (inset * 2);

            // Create a gradient effect from light blue to dark blue
            GradientPaint gradient = new GradientPaint(
                    0, inset, new Color(0x4A90E2),
                    0, innerH, new Color(0x1360C4)
            );

            g2d.setPaint(gradient);

            // This is the inner progress bar
            int innerArc = Math.max(0, arcSize - 4);
            g2d.fillRoundRect(inset, inset, innerW, innerH, innerArc, innerArc);
        }

        g2d.dispose();
    }
}