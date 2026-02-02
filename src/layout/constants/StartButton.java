package layout.constants;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class StartButton extends JButton {
    private boolean isHovered = false;
    private boolean isPressed = false;

    public StartButton() {
        this.setText("start");
        this.setFont(HangFonts.loadCustomFonts(Font.BOLD | Font.ITALIC, 24));
        this.setForeground(Color.WHITE);
        this.setLayout(new FlowLayout());

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isHovered = true;
                repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                isHovered = false;
                repaint();
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                isPressed = true;
                repaint();
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                isPressed = false;
                repaint();
            }
        });

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        Color greenTop = isPressed ? HangColors.startPressedTop : HangColors.startDefaultTop;
        Color greenBottom = isPressed ? HangColors.startPressedBot : HangColors.startDefaultBot;

        if (isHovered && !isPressed) {
            greenTop = HangColors.startHoverTop;
            greenBottom = HangColors.startHoverBottom;
        }

        Path2D buttonShape = new Path2D.Double();
        int arcSize = 15;
        buttonShape.moveTo(0, 0);
        buttonShape.lineTo(width - arcSize, 0);
        buttonShape.quadTo(width, 0, width, arcSize);
        buttonShape.lineTo(width, height - arcSize);
        buttonShape.quadTo(width, height, width - arcSize, height);
        buttonShape.lineTo(0, height);
        buttonShape.closePath();

        // Fill
        GradientPaint greenGradient = new GradientPaint(0, 0, greenTop, 0, height, greenBottom);
        g2d.setPaint(greenGradient);
        g2d.fill(buttonShape);

        // Padding & scaling
        int iconSize = (int) (height * 0.50);
        int iconY = (height - iconSize) / 2;
        int horizontalPadding = (int) (height * 0.20);

        // Draw Image
        if (HangImages.windows != null) {
            g2d.drawImage(HangImages.windows, horizontalPadding, iconY, iconSize + 5, iconSize, this);
        }

        // Highlight effect
        if (!isPressed) {
            Path2D highlightShape = new Path2D.Double();
            highlightShape.moveTo(0, 0);
            highlightShape.lineTo(width - arcSize, 0);
            highlightShape.quadTo(width, 0, width, arcSize);
            highlightShape.lineTo(width, (double) height / 2);
            highlightShape.lineTo(0, (double) height / 2);
            highlightShape.closePath();

            GradientPaint highlightGradient = new GradientPaint(
                    0, 0, new Color(255, 255, 255, 80),
                    0, (float) height / 3, new Color(255, 255, 255, 0)
            );
            g2d.setPaint(highlightGradient);
            g2d.fill(highlightShape);
        }

        // Text position
        g2d.setColor(Color.WHITE);
        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();

        int textX = horizontalPadding + iconSize + horizontalPadding;
        int textY = (height + fm.getAscent() - fm.getDescent()) / 2;

        g2d.drawString(getText(), textX, textY);
    }
}