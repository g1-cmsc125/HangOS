package layout.design.designStartElements;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    GameLogic gameLogic;
    VirtualKeyboard virtualKeyboard;
    HangmanPanel hangmanPanel;

    public GamePanel(GameLogic gameLogic){
        this.gameLogic = gameLogic;
        this.hangmanPanel = gameLogic.hangmanPanel;

        this.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        virtualKeyboard = new VirtualKeyboard(gameLogic);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Allow horizontal stretching
        gbc.weighty = 0.95; // Give each component 50% of the vertical space
        this.add(hangmanPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0; // Allow horizontal stretching
        gbc.weighty = 0.05; // Give each component 50% of the vertical space
        this.add(virtualKeyboard, gbc);

        this.setVisible(true);
    }
}
