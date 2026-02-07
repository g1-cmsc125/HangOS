package logic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel implements ActionListener  {
CardPanel cardPanel;
JButton start;

MenuPanel(CardPanel cardPanel){
 this.cardPanel = cardPanel; 

start =  new JButton("Start");

start.setBounds(200, 258, 180, 50);

start.addActionListener(this);

this.add(start); 

}



@Override
public void actionPerformed(ActionEvent e) {
    if(e.getSource() == start){
        cardPanel.showGamePanel(); 
        
    }
}

    
}
