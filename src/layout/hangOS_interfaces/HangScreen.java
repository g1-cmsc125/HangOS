package layout.hangOS_interfaces;

import layout.design.Design;

import javax.swing.*;

public abstract class HangScreen extends JPanel implements HangInterface{
    @Override
    public void displayCenter() { }

    // Override with Design.footer
    @Override
    public void displayBottom(JPanel mainPanel) {
        Design.footerDesign(mainPanel);
    }
}