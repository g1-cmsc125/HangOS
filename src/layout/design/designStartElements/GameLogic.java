package layout.design.designStartElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import layout.constants.HangFonts;
import layout.constants.MiniWindow;

public class GameLogic extends JPanel{
    String word = "";
    int wrongGuesses = 0;
    private int maxMistakes= 6;
    ArrayList<Character> wordState = new ArrayList<>();
    public HangmanPanel hangmanPanel;
    VirtualKeyboard virtualKeyboard;

    String[] virusTitles = {"System Error", "Trojan.exe", "Warning", "Critical Failure", "Memory Leak"};
    String[] virusMessages = {
            "Deleting System32...",
            "Illegal operation detected.",
            "RAM download failed.",
            "Your IP has been logged.",
            "Windows cannot restart.",
            "Fatal Exception 0E has occurred."
    };

    public GameLogic(HangmanPanel hangmanPanel){
        this.hangmanPanel = hangmanPanel;
        getRandomWord();
    }


    public void getRandomWord(){
        String filepath = "database.txt";
        ArrayList<String> words = new ArrayList<>();

        try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){
            String line;
            while((line = reader.readLine())!=null){
                words.add(line.trim());
            }

        }catch(FileNotFoundException e){
            System.out.println("No file");
        }catch(IOException e){
            System.out.println("Something went wrong" + "File not found at: " + System.getProperty("user.dir"));
        }

        if (!words.isEmpty()) {
            Random random = new Random();
            word = words.get(random.nextInt(words.size())).toLowerCase();
        } else {
            word = "Operating System";
            System.out.println("Warning: Using fallback word 'hangman'");
        }

        System.out.println("Secret Word: " + word);
        initializeWordState();
    }

    public void initializeWordState() {
        wordState.clear();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == ' ') {
                wordState.add(' ');
            } else {
                wordState.add('_');
            }
        }
        hangmanPanel.displayWord(wordState);
    }


    public void checkWord(String var1) {
        char guess = var1.toLowerCase().charAt(0);

        if (word.indexOf(guess) >= 0) {
            // correct guess
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guess) {
                    wordState.set(i, guess);
                }
            }
            hangmanPanel.displayWord(wordState);

            if (!wordState.contains('_')) {
                // TODO: add win logic!!!!
                JOptionPane.showMessageDialog(this, "Download Complete: " + word + ".exe");
                
            }

        } else {
            // wrong guess
            wrongGuesses++;
            
            // spawning virus
            spawnVirus();

            // will check if crash or end game is done
            if (wrongGuesses >= maxMistakes) {
                // TODO: add endgame logic
                System.out.println("CRASH TRIGGERED!"); 
            }
        }
    }


    public void wrongGuessLimit(){
        if(wrongGuesses>=6){
            System.out.println("Start Over");
            System.out.println("The word was: " + word);
        }else{
        }
    }


    public void handleGuess(String letter) {
        System.out.println("The user clicked: " + letter);
        checkWord(letter);

    }

    public int getMaxMistakes() {
        return maxMistakes;
    }

    public void setMaxMistakes(int maxMistakes) {
        this.maxMistakes = maxMistakes;
    }

    // popup logic

    private void spawnVirus(){
        Random rand = new Random();
        String title = virusTitles[rand.nextInt(virusTitles.length)];
        String msg = virusMessages[rand.nextInt(virusMessages.length)];
        
        spawnPopup(title, msg, Color.white);
    }

    private void spawnPopup(String title, String msg, Color bgColor){
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(bgColor);

        // change this icon to the right one
        JLabel icon = new JLabel(UIManager.getIcon("OptionPane.errorIcon"));

        JTextArea text = new JTextArea(msg);
        text.setFont(HangFonts.loadCustomFonts(Font.PLAIN, 12));
        text.setWrapStyleWord(true);
        text.setLineWrap(true);
        text.setOpaque(false);
        text.setEditable(false);
        text.setPreferredSize(new Dimension(180, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 10, 10, 10);
        contentPanel.add(icon, gbc);
        contentPanel.add(text, gbc);

        MiniWindow mw = new MiniWindow(title, 300, 150, contentPanel);

        JDialog popup = new JDialog();
        popup.setUndecorated(true); 
        popup.setBackground(new Color(0, 0, 0, 0)); 
        popup.setContentPane(mw); 
        popup.pack();

        Random rand = new Random();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = rand.nextInt(screenSize.width - 300);
        int y = rand.nextInt(screenSize.height - 150);
        popup.setLocation(x, y);

        popup.setAlwaysOnTop(true);
        popup.setVisible(true);
    }
}