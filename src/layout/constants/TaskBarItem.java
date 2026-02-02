package layout.constants;

import javax.swing.*;
import java.awt.*;

public class TaskBarItem extends JToggleButton {
    boolean isActiveWindow;

    public TaskBarItem(String text, boolean isActiveWindow) {
        super(text);
        this.isActiveWindow = isActiveWindow;

        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);

        this.setFont(HangFonts.loadCustomFonts(Font.PLAIN, 11));
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setMargin(new Insets(0, 5, 0, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Colors
        Color topColor, bottomColor, borderColor;

        if (isActiveWindow) {
            topColor = new Color(30, 75, 145);
            bottomColor = new Color(50, 105, 185);
            borderColor = new Color(20, 45, 100);
        } else {
            topColor = new Color(110, 170, 235);
            bottomColor = new Color(60, 125, 210);
            borderColor = new Color(45, 90, 170);
        }

        // Background
        GradientPaint bgPaint = new GradientPaint(0, 0, topColor, 0, h, bottomColor);
        g2d.setPaint(bgPaint);
        g2d.fillRoundRect(0, 0, w - 1, h - 1, 4, 4);

        // Highlight (Inactive only)
        if (!isActiveWindow) {
            g2d.setColor(new Color(255, 255, 255, 70));
            g2d.drawLine(2, 1, w - 3, 1);
        }

        // Border
        g2d.setColor(borderColor);
        g2d.drawRoundRect(0, 0, w - 1, h - 1, 4, 4);

        // Placeholder Icon
        if (getIcon() == null) {
            drawPlaceholderIcon(g2d);
        }

        // Text
        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();
        int textX = 26;
        // Vertically center text
        int textY = (h + fm.getAscent() - fm.getDescent()) / 2 + 1;

        g2d.setColor(Color.WHITE);
        g2d.drawString(getText(), textX, textY);

        g2d.dispose();
    }

    private void drawPlaceholderIcon(Graphics2D g2d) {
        int iconX = 6;
        int iconY = (getHeight() - 14) / 2;

        g2d.setColor(new Color(235, 205, 100));
        g2d.fillRect(iconX, iconY + 2, 14, 10);
        g2d.fillRect(iconX + 2, iconY, 6, 3);
        g2d.setColor(new Color(180, 150, 50));
        g2d.drawRect(iconX, iconY + 2, 14, 10);
    }
}