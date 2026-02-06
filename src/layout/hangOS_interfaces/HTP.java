package layout.hangOS_interfaces;

import layout.constants.HangImages;
import layout.design.Design;

import javax.swing.*;
import java.awt.*;

/* How to play page */
public class HTP extends HangScreen {
    public HTP (){
        this.setLayout(new BorderLayout());

        displayCenter();
        displayBottom(this);
    }

    // Implement how the center screen looks here
    public void displayCenter(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);

        Design.centerDesignDefault(this, centerPanel);
    }

    // Paint bg-image
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(HangImages.background, 0, 0, getWidth(), (int) (getHeight() * 0.90), this);
    }
}
