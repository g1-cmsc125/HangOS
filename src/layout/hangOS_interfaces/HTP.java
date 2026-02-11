package layout.hangOS_interfaces;

import layout.constants.HangImages;
import layout.design.Design;
import layout.design.DesignHTP;

import javax.swing.*;
import java.awt.*;

/* How to play page */
public class HTP extends HangScreen {
    public HTP (){
        this.setLayout(new BorderLayout());
        displayCenter();
    }

    public void displayCenter(){
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);

        // This adds the instructions to the center of THIS panel
        Design.centerDesignDefault(this, centerPanel);
        DesignHTP.displayInstructionWindow(centerPanel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(HangImages.background, 0, 0, getWidth(), getHeight(), this);
        g2d.dispose();
    }
}