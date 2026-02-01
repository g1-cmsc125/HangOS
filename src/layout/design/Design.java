package layout.design;

import javax.swing.*;
import java.awt.*;

public class Design {
    public static int screenWidth = 720;
    public static int screenHeight = 512;

    // Preferred size for arrangement of elements
    // Not concerned with the painted bg image
    public static void centerDesignDefault(JPanel mainPanel, JPanel centerPanel) {

        centerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.90)));
        centerPanel.setOpaque(false);
        mainPanel.add(centerPanel, BorderLayout.NORTH);
    }

    public static void footerDesign(JPanel mainPanel) {
        JPanel bottomPanel = new JPanel(new GridLayout(1, 3));

        for(int i = 0; i < 3; i++) {
            JButton button = new JButton("Button " + i);
            bottomPanel.add(button);
        }

        bottomPanel.setPreferredSize(new Dimension(Design.screenWidth, (int)(Design.screenHeight * 0.10)));
        bottomPanel.setOpaque(false);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
    }
}
