package layout.hangOS_interfaces;

import java.awt.*;
import javax.swing.*;
import layout.constants.HangImages;
import layout.design.Design;
import layout.design.DesignStart;

/* Place the design for the game here in displayCenter() */
public class Start extends HangScreen implements HangInterface {
    public Start(){
        this.setLayout(new BorderLayout());
        displayCenter();
    }

    // Implement how the center screen looks here
    public void displayCenter(){
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);

        Design.centerDesignDefault(this, centerPanel);
        DesignStart.displayHangOS(centerPanel);
    }

    // Paint bg-image
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(HangImages.background, 0, 0, getWidth(), getHeight(), this);
        g2d.dispose();
    }
}