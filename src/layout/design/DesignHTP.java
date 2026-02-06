package layout.design;

import layout.constants.MiniWindow;

import javax.swing.*;

public class DesignHTP {
    public static void displayInstructionWindow(JPanel centerPanel) {
        MiniWindow mw = new MiniWindow("title", "hey");
        centerPanel.add(mw);
    }
}
