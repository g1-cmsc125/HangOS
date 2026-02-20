package layout.design.designStartElements;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import layout.constants.HangCustomTahoma;
import layout.design.DesignStart;

public class VirtualKeyboard extends JPanel {
    ArrayList<JButton> buttonList = new ArrayList<>();
    private final String[] row1 = "QWERTYUIOP".split("");
    private final String[] row2 = "ASDFGHJKL".split("");
    private final String[] row3 = "ZXCVBNM".split("");
    GameLogic gameLogic;

    // Custom colors
    private final Color brandBlue = new Color(30, 110, 230);
    private final Color bgBlue = new Color(0xd2e4fa);
    private final Color pressedBlue = new Color(90, 160, 240);

    // This creates the virtual keyboard
    public VirtualKeyboard() {

        // Add the game logic to the keyboard
        this.gameLogic = DesignStart.gameLogic;

        // Create layout of the keyboard
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(35, 25, 35, 25));
        setOpaque(false);

        // Create the three rows with vertical spacing
        add(createRowPanel(row1, gameLogic));
        add(Box.createVerticalStrut(6)); // Vertical gap between row 1 an 2
        add(createRowPanel(row2, gameLogic));
        add(Box.createVerticalStrut(6)); // Vertical gap between row 2 and 3
        add(createRowPanel(row3, gameLogic));
    }

    // This creates the keyboard by row
    private JPanel createRowPanel(String[] keys, GameLogic logic) {

        // FlowLayout handles horizontal gaps
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        panel.setOpaque(false);

        // Loops through each character to create the tiles design
        for (String keyText : keys) {
            JButton btn = createRetroButton(keyText);
            btn.setPreferredSize(new Dimension(70, 45));

            // Actions for each key pressed
            btn.addActionListener(e -> {
                gameLogic.handleGuess(keyText);
                // Disable button if pressed
                btn.setEnabled(false);
            });

            // Add
            buttonList.add(btn);
            panel.add(btn);
        }
        return panel;
    }

    // This creates the letter buttons individually
    private JButton createRetroButton(String text) {
        resetKeyboard(); 
        JButton btn = new JButton(text) {
            
            // Use 2d for advanced design
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Determine button state
                if (!isEnabled()) { // Disabled
                    g2.setColor(pressedBlue);
                } else if (getModel().isPressed()) { // Pressed
                    g2.setColor(new Color(230, 240, 255));
                } else { // Default
                    g2.setColor(Color.WHITE);
                }
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 12, 12);

                // Draw Text
                g2.setFont(getFont());
                FontMetrics fm = g2.getFontMetrics();
                int x = (getWidth() - fm.stringWidth(getText())) / 2;
                int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();

                // Set text color (Black if disabled/pressed, blue if active)
                if (!isEnabled()) {
                    g2.setColor(Color.BLACK);
                } else {
                    g2.setColor(brandBlue);
                }
                g2.drawString(getText(), x, y);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setStroke(new BasicStroke(2.0f));
                g2.setColor(brandBlue);
                g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, 12, 12);
                g2.dispose();
            }
        };

        btn.setFont(HangCustomTahoma.loadCustomFonts(Font.PLAIN, 22));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return btn;
    }

    public void resetKeyboard() {
        for (JButton btn : buttonList) {
            btn.setEnabled(true);
        }
    }

    public void setGameLogic(GameLogic logic) {
        this.gameLogic = logic;
    }


    // Paint component of the whole keyboard panel
    @Override
    protected void paintComponent(Graphics g) {
        // Create 2D graphics for the whole panel
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set color of the background
        g2.setColor(new Color(0x2A73C8));
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Define the margins
        int marginX = 15; // Left and right gap size
        int marginY = 15; // Top and bottom gap size

        // Draw the inner rounded area
        g2.setColor(bgBlue);
        g2.fillRoundRect(marginX, marginY, getWidth() - (marginX * 2), getHeight() - (marginY * 2), 25, 25);
        g2.dispose();
    }
}