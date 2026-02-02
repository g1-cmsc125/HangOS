package layout.constants;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class StartButton extends JButton {
    private boolean isHovered = false;
    private boolean isPressed = false;

    public StartButton() {
        this.setText("start");
        this.setFont(HangFonts.START_BUTTON);
        this.setForeground(Color.WHITE);
        this.setLayout( new FlowLayout());

        // Separate listener concerns
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

        int width = getWidth(); int height = getHeight();

        // State color adjustment for StartButton
        Color greenTop = isPressed ? new Color(90, 160, 50) : new Color(115, 190, 70);
        Color greenBottom = isPressed ? new Color(50, 130, 40) : new Color(60, 150, 50);

        if (isHovered && !isPressed) {
            greenTop = new Color(130, 200, 85);
            greenBottom = new Color(70, 160, 60);
        }

        // Straight left corners and rounded right corners
        Path2D buttonShape = new Path2D.Double();
        int arcSize = 15;
        buttonShape.moveTo(0, 0);
        buttonShape.lineTo(width - arcSize, 0); // Top edge to top-right (before curve)
        buttonShape.quadTo(width, 0, width, arcSize); // Top-right rounded corner
        buttonShape.lineTo(width, height - arcSize); // Right edge to bottom-right (before curve)
        buttonShape.quadTo(width, height, width - arcSize, height); // Bottom-right rounded corner
        buttonShape.lineTo(0, height); // Bottom edge to bottom-left (straight corner)
        buttonShape.closePath(); // Left edge back to top-left (straight corner)

        // Gradient fill
        GradientPaint greenGradient = new GradientPaint(
                0, 0, greenTop,
                0, height, greenBottom
        );
        g2d.setPaint(greenGradient);
        g2d.fill(buttonShape);

        // Replace with image here
        g2d.drawImage(HangImages.windows, (int)(getWidth()*0.05), (int)(getHeight()/4.5), (int)(getWidth()/3.5), (int)(getHeight() / 1.75),this);

        System.out.println(getWidth());
        System.out.println(getHeight());

        // Highlight effect on top
        if (!isPressed) {
            Path2D highlightShape = new Path2D.Double();
            highlightShape.moveTo(1, 1);
            highlightShape.lineTo(width - arcSize - 1, 1);
            highlightShape.quadTo(width - 2, 1, width - 2, arcSize);
            highlightShape.lineTo(width - 2, (double) height /2);
            highlightShape.lineTo(1, (double) height /2);
            highlightShape.closePath();

            GradientPaint highlightGradient = new GradientPaint(
                    0, 0, new Color(255, 255, 255, 80),
                    0, (float) height /3, new Color(255, 255, 255, 0)
            );
            g2d.setPaint(highlightGradient);
            g2d.fill(highlightShape);
        }



        // Draw text manually for better control
        g2d.setColor(Color.WHITE);
        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();
        int textX = 45;
        int textY = (height + fm.getAscent() - fm.getDescent()) / 2;
        g2d.drawString(getText(), textX, textY);
    }
}