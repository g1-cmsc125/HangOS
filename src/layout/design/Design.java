package layout.design;

import javax.swing.*;
import java.awt.*;

public class Design {
    public static int screenWidth = 720;
    public static int screenHeight = 512;

    // default design for center-part, 80 percent cover
    public static void centerDefault(JPanel mainPanel, JPanel centerPanel) {
        // Add dynamic height for center if Footer / Header are dynamic as well, otherwise same size of screen
        // centerPanel.setPreferredSize(new Dimension(Design.screenWidth, (int) (Design.screenHeight * 0.80)));
        centerPanel.setPreferredSize(new Dimension(Design.screenWidth, Design.screenHeight));
        centerPanel.setOpaque(false);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
    }
}
