package logic;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class CardPanel extends JPanel {
    JPanel cardsPanel;
    CardLayout cardLayout; 
    GameLogic gameLogic; 
    MenuPanel menuPanel; 
    GamePanel gamePanel; 
    HangmanPanel hangmanPanel; 

    public CardPanel(){
        hangmanPanel = new HangmanPanel(); 
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        gameLogic = new GameLogic(this, hangmanPanel); 
        

        gamePanel = new GamePanel(this,gameLogic); 
        menuPanel = new MenuPanel(this); 

         
        cardsPanel.add(gamePanel, "gamePanel"); 
        cardsPanel.add(menuPanel, "menuPanel"); 

        this.setLayout(new BorderLayout());
        this.add(cardsPanel, BorderLayout.CENTER);

    }

    
    public void showGamePanel(){


        cardLayout.show(cardsPanel, "gamePanel"); 
        gameLogic.getRandomWord();
        //new VirtualKeyboard();
        //VirtualKeyboard.resetKeyboard();
    }

    public void showMenuPanel(){
        cardLayout.show(cardsPanel, "menuPanel"); 
    }
}