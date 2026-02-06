package layout.constants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MiniWindow extends JPanel {

    public MiniWindow(String title, String message) {
        // 1. Fix the Size: Use PreferredSize to prevent the layout from crushing it
        setPreferredSize(new Dimension(300, 150));
        setLayout(new BorderLayout());

        // Classic XP Blue Border
        setBorder(new LineBorder(new Color(0, 84, 227), 2));
        setBackground(new Color(236, 233, 216));

        // --- TITLE BAR ---
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(0, 84, 227));
        titleBar.setPreferredSize(new Dimension(0, 28));

        JLabel titleLabel = new JLabel("  " + title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        titleBar.add(titleLabel, BorderLayout.WEST);

        // Control Buttons (Minimize, Maximize, Close)
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 4));
        controls.setOpaque(false);
        controls.add(createHeaderBtn("_"));
        controls.add(createHeaderBtn("▢"));
        controls.add(createHeaderBtn("X", new Color(232, 17, 35))); // Red Close

        titleBar.add(controls, BorderLayout.EAST);
        add(titleBar, BorderLayout.NORTH);

        // --- MESSAGE CONTENT ---
        JLabel msgLabel = new JLabel(message, SwingConstants.CENTER);
        msgLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        add(msgLabel, BorderLayout.CENTER);

        // OK Button at the bottom
        JButton okBtn = new JButton("OK");
        okBtn.setFocusPainted(false);
        JPanel btnPanel = new JPanel();
        btnPanel.setOpaque(false);
        btnPanel.add(okBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private JButton createHeaderBtn(String text) { return createHeaderBtn(text, new Color(70, 130, 255)); }

    private JButton createHeaderBtn(String text, Color bg) {
        JButton b = new JButton(text);
        b.setPreferredSize(new Dimension(21, 21));
        b.setMargin(new Insets(0, 0, 0, 0));
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Tahoma", Font.BOLD, 10));
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        return b;
    }
}