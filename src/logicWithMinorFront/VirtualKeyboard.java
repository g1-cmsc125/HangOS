package logicWithMinorFront;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VirtualKeyboard extends JPanel {
    private String[] letters = "QWERTYUIOPASDFGHJKLZXCVBNM".split("");
    // 1. Store the buttons here
    private ArrayList<JButton> buttonList = new ArrayList<>();

    public VirtualKeyboard(GameLogic gameLogic) {
        setLayout(new GridLayout(3, 10, 5, 5)); 
        this.setPreferredSize(new Dimension(400, 200));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 

        for (String letter : letters) {
            JButton key = new JButton(letter);
            key.setFont(new Font("Arial", Font.BOLD, 12));
            
            key.addActionListener(e -> {
                gameLogic.handleGuess(letter);
                key.setEnabled(false); 
            });

            // 2. Add each key to our list
            buttonList.add(key);
            add(key);
        }
    }

    // 3. Create a reset method
    public void resetKeyboard() {
        for (JButton btn : buttonList) {
            btn.setEnabled(true);
        }
    }
}