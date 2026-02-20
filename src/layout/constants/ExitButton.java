package layout.constants;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import javax.swing.*;

public class ExitButton extends JButton {
    private boolean isHovered = false;
    private boolean isPressed = false;

    public ExitButton() {
        this.setFont(HangFonts.loadCustomFonts(Font.BOLD | Font.ITALIC, HangFonts.titleFontSize));
        this.setText("exit");
        this.setForeground(Color.WHITE);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                isHovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                isHovered = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent evt) {
                SoundManager.playSystemSound("Windows XP Shutdown.wav");
                isPressed = true;
                repaint();

                Window window = SwingUtilities.getWindowAncestor(ExitButton.this);
                if (window instanceof JFrame) {
                    playExitGifAndExit((JFrame) window);
                } else {
                    System.exit(0);
                }
            }
        });
    }

    private void playExitGifAndExit(JFrame frame) {
        // Reset the image to start from frame 1
        if (HangImages.splash != null) {
            HangImages.splash.flush();
        }

        // Use the URL or the Image directly in ImageIcon (ImageIcon supports animation)
        JPanel glass = getJPanel(frame);
        glass.repaint();

        // 3000ms = 3 seconds. Adjust to your GIF's length.
        Timer timer = new Timer(5000, e -> System.exit(0));
        timer.setRepeats(false);
        timer.start();
    }

    private static JPanel getJPanel(JFrame frame) {
        ImageIcon exitIcon = new ImageIcon(HangImages.splash);
        JLabel gifLabel = new JLabel(exitIcon);

        // Setup GlassPane to cover the entire UI
        JPanel glass = (JPanel) frame.getGlassPane();
        glass.setLayout(new GridBagLayout()); // Centers the GIF
        glass.removeAll();
        glass.setBackground(Color.BLACK);
        glass.setOpaque(true);
        glass.add(gifLabel);

        // Force the label to observe and animate the GIF
        exitIcon.setImageObserver(gifLabel);

        glass.setVisible(true);
        glass.revalidate();
        return glass;
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

        GradientPaint greenGradient = new GradientPaint(0, 0, greenTop, 0, height, greenBottom);
        g2d.setPaint(greenGradient);
        g2d.fill(buttonShape);

        int iconSize = (int) (height * 0.50);
        int iconY = (height - iconSize) / 2;
        int horizontalPadding = (int) (height * 0.20);

        if (HangImages.windows != null) {
            g2d.drawImage(HangImages.windows, horizontalPadding, iconY, iconSize + 5, iconSize, this);
        }

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

        g2d.setColor(Color.WHITE);
        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();
        int textX = horizontalPadding + iconSize + horizontalPadding;
        int textY = (height + fm.getAscent() - fm.getDescent()) / 2;
        g2d.drawString(getText(), textX, textY);
    }
}