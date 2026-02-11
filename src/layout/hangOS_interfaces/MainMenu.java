package layout.hangOS_interfaces;

import layout.design.Design;
import layout.constants.HangImages;
import layout.design.DesignMenu;
import java.awt.*;
import javax.swing.*;

public class MainMenu extends HangScreen {
    public MainMenu(){
        this.setLayout(new BorderLayout());
        displayCenter();
    }

    public void displayCenter(){
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);

        Design.centerDesignDefault(this, centerPanel);
        DesignMenu.displayMenuButtons(centerPanel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(HangImages.background, 0, 0, getWidth(), getHeight(), this);
        g2d.dispose();
    }
}