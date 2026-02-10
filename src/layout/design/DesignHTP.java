package layout.design;

import layout.constants.HangColors;
import layout.constants.MiniWindow;

import javax.swing.*;
import java.awt.*;

public class DesignHTP {
    public static void displayInstructionWindow(JPanel centerPanel) {
        centerPanel.setLayout(new BorderLayout());

        // Customizable JPanel inside MiniWindow
        JPanel inWindowPanel = new JPanel(new BorderLayout());
        JLabel test = new JLabel("This JPanel is customizable.", SwingConstants.CENTER);
        test.setForeground(Color.BLACK);
        inWindowPanel.add(test, BorderLayout.CENTER);

        MiniWindow mw = new MiniWindow("Instructions",245, 245, inWindowPanel);

        // Wrapper for multi-dir-anchor purposes
        JPanel southWrapper = new JPanel();
        southWrapper.setLayout(new BorderLayout());
        southWrapper.add(mw, BorderLayout.SOUTH);
        southWrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 60, 30));
        southWrapper.setOpaque(false);

        centerPanel.add(southWrapper, BorderLayout.EAST);
    }
}
