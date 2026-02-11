package layout.hangOS_interfaces;

import javax.swing.*;

public abstract class HangScreen extends JPanel implements HangInterface{
    public HangScreen() {
        this.setOpaque(false);
    }

    @Override
    public void displayCenter() {
    }

    @Override
    public void displayBottom(JPanel mainPanel) {
    }
}