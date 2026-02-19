package layout.design.designStartElements;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import layout.constants.HangFonts;
import layout.constants.MiniWindow;

public class GameLogic extends JPanel {
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
        startNewGame();
    }

    public void startNewGame() {
        this.wrongGuesses = 0;
        this.getRandomWord();
        resetKeyboardActions();
        // Clear any existing viruses from the screen if needed
        Window win = SwingUtilities.getWindowAncestor(this.hangmanPanel);
        if (win instanceof RootPaneContainer) {
             win.repaint();
        }
    }


    public void getRandomWord(){
        String filepath = "database.txt";
        ArrayList<String> words = new ArrayList<>();

        try (InputStream is = getClass().getResourceAsStream(filepath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            if (is == null) {
                System.out.println("Error: Could not find database.txt in classpath.");
                // Determine fallback behavior here
            } else {
                String line;
                while ((line = reader.readLine()) != null) {
                    words.add(line.trim());
                }
            }

        }catch(FileNotFoundException e){
            System.out.println("No file1");
            System.out.println("File not found!");
            System.out.println("I am looking for: src/logic/database.txt");
            System.out.println("But I am currently standing in: " + System.getProperty("user.dir"));
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
        hangmanPanel.updateProgress(0, getTotalLetters());
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

            // Calculate the correct and total words to be shown on HangmanPanel
            int correct = getCorrectLetters();
            int total = getTotalLetters();
            hangmanPanel.updateProgress(correct, total);

            if (!wordState.contains('_')) {
                // TODO: add win logic!!!!
                JOptionPane.showMessageDialog(this, "Download Complete: " + word + ".exe");
                
            }

        } else {
            // wrong guess
            wrongGuesses++;
            
            // spawning virus
            spawnInternalVirus();

            // will check if crash or end game is done
            if (wrongGuesses >= maxMistakes) {
                triggerSystemCrash();
                System.out.println("CRASH TRIGGERED!"); 
            }
        }
    }


    // public void wrongGuessLimit(){
    //     if(wrongGuesses>=6){
    //         System.out.println("Start Over");
    //         System.out.println("The word was: " + word);
    //     }else{
    //     }
    // }

    public void triggerSystemCrash(){
        Timer stormTimer = new Timer(100, new ActionListener() {
            int count = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnInternalVirus();
                count++;
                if (count >= 60) { //change number to make it more
                    ((Timer)e.getSource()).stop();
                    triggerBlackout();
                }
            }
        });
        stormTimer.start();
    }

    private void triggerBlackout() {
        Window win = SwingUtilities.getWindowAncestor(this.hangmanPanel);
        if (!(win instanceof RootPaneContainer)) return;

        JLayeredPane layers = ((RootPaneContainer) win).getLayeredPane();
        
        JPanel blackScreen = new JPanel(new GridBagLayout());
        blackScreen.setBackground(Color.BLACK);
        blackScreen.setBounds(0, 0, win.getWidth(), win.getHeight());
        
        JLabel text = new JLabel("> SYSTEM FAILURE. REBOOTING...");
        text.setForeground(Color.GREEN);
        text.setFont(new Font("Consolas", Font.BOLD, 14));
        blackScreen.add(text);

        layers.add(blackScreen, JLayeredPane.DRAG_LAYER);
        layers.revalidate();
        layers.repaint();

        Timer rebootTimer = new Timer(3000, e -> {
            ((Timer)e.getSource()).stop();
            
            // A. Remove Black Screen
            layers.remove(blackScreen);

            Component[] components = layers.getComponentsInLayer(JLayeredPane.MODAL_LAYER);
            for (Component c : components) {
                if (c instanceof MiniWindow) {
                    layers.remove(c);
                }
            }
            layers.repaint(); 
            startNewGame();
            layout.Card.screenChoice("Main Menu");
        });
        rebootTimer.setRepeats(false);
        rebootTimer.start();
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
    private void spawnInternalVirus() {
        Random rand = new Random();

        // main container panel
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.WHITE);
        JTextArea text = new JTextArea(virusMessages[rand.nextInt(virusMessages.length)]);
        text.setFont(HangFonts.loadCustomFonts(Font.PLAIN, 12));
        text.setWrapStyleWord(true); text.setLineWrap(true);
        text.setEditable(false); text.setOpaque(false);
        content.add(new JLabel(UIManager.getIcon("OptionPane.errorIcon")));
        content.add(text);

        // mini window 
        String title = virusTitles[rand.nextInt(virusTitles.length)];
        MiniWindow mw = new MiniWindow(title, 220, 100, content);

        // finding main window
        Window window = SwingUtilities.getWindowAncestor(this.hangmanPanel);
        
        if (window instanceof RootPaneContainer) {
            JLayeredPane layeredPane = ((RootPaneContainer) window).getLayeredPane();
            
            // random pos
            int x = rand.nextInt(Math.max(1, window.getWidth() - 250));
            int y = rand.nextInt(Math.max(1, window.getHeight() - 150));
            
            mw.setBounds(x, y, 220, 100);

            // adding modal layer so it sits on top
            layeredPane.add(mw, JLayeredPane.MODAL_LAYER);
            layeredPane.moveToFront(mw);
            
            // force repaint.
            layeredPane.repaint();
            
            System.out.println("Virus spawned at " + x + ", " + y); // Debug check
        } else {
            System.out.println("Could not find Main Window! Is the panel added to the frame?");
        }
    }


    // To get the total number of letters
    private int getTotalLetters() {
        int count = 0;
        for (char c : word.toCharArray()) {
            if (c != ' ') count++;
        }
        return count;
    }

    // Count the correct letters guessed
    private int getCorrectLetters() {
        int count = 0;
        for (char c : wordState) {
            if (c != '_' && c != ' ') count++;
        }
        return count;
    }

}