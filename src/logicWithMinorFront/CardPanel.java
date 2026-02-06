import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.awt.event.KeyAdapter;

public class CardPanel extends JPanel {
    JPanel cardsPanel;
    CardLayout cardLayout; 
    GameLogic gameLogic; 
    MenuPanel menuPanel; 
    GamePanel gamePanel; 
    HangmanPanel hangmanPanel; 

    CardPanel(){
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
        gamePanel.virtualKeyboard.resetKeyboard();
    }

    public void showMenuPanel(){
        cardLayout.show(cardsPanel, "menuPanel"); 
    }
}