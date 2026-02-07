package logic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
CardPanel cardPanel; 
GameLogic gameLogic;
VirtualKeyboard virtualKeyboard; 
HangmanPanel hangmanPanel;  

GamePanel(CardPanel cardPanel, GameLogic gameLogic){
    this.cardPanel = cardPanel; 
    this.gameLogic = gameLogic; 
    this.hangmanPanel = gameLogic.hangmanPanel;

    this.setLayout(new GridBagLayout());

    GridBagConstraints gbc = new GridBagConstraints(); 
    virtualKeyboard = new VirtualKeyboard(gameLogic); 

    gbc.fill = GridBagConstraints.BOTH; 
    gbc.gridy = 0; 
    gbc.weightx = 1.0; // Allow horizontal stretching
    gbc.weighty = 0.8; // Give each component 50% of the vertical space
    this.add(hangmanPanel, gbc); 


    gbc.fill = GridBagConstraints.BOTH; 
    gbc.gridy=1; 
    gbc.weightx = 1.0; // Allow horizontal stretching
    gbc.weighty = 0.2; // Give each component 50% of the vertical space
    this.add(virtualKeyboard, gbc); 
    

    this.setVisible(true);



}

}
