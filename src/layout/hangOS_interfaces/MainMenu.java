package layout.hangOS_interfaces;

import layout.design.Design;
// import layout.design.DesignMenu;
import layout.constants.HangImages;

import java.awt.*;

import javax.swing.*;

public class MainMenu extends HangScreen {
    public MainMenu(){
        this.setLayout(new BorderLayout());

        displayCenter();
    }

    // Implement how the center screen looks here
    public void displayCenter(){
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Design.centerDefault(this, centerPanel);
        // DesignMenu.optionsDesign(centerPanel, gbc);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(HangImages.background, 0, 0, getWidth(), getHeight(), this);
    }
}