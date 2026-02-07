package logic;

import javax.swing.*;
import java.awt.BorderLayout;

public class MainFrame extends JFrame{
    CardPanel cardPanel; 
    public MainFrame() {
        this.setTitle("Hangman");
        this.setDefaultCloseOperation(3);
        this.setSize(500, 350);
        this.setLayout(new BorderLayout());
        cardPanel = new CardPanel(); 
        this.add(cardPanel); 
        cardPanel.showMenuPanel(); 

        this.setLocationRelativeTo(null);
        this.setResizable(false); 
        
    }
}