package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GameLogic extends JPanel{
    String word = "";
    int wrongGuesses = 0;
    public CardPanel cardPanel;
    private int maxMistakes= 6;
    ArrayList<Character> wordState = new ArrayList<>();
    public HangmanPanel hangmanPanel;
    VirtualKeyboard virtualKeyboard;

    public GameLogic(CardPanel cardPanel, HangmanPanel hangmanPanel){
        this.cardPanel = cardPanel;
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


    public void checkWord(String var1){

        System.out.println("Hangman by Cardi B");
        System.out.println();

        System.out.print("Guess a letter: ");
        char guess = var1.toLowerCase().charAt(0);

        System.out.println(guess);



        System.out.println();

        if(word.indexOf(guess)>= 0){
            System.out.print("Corrert" + "\n");


            for(int i = 0; i< word.length(); i++){
                if(word.charAt(i) == guess){
                    wordState.set(i, guess);
                }
            }

            for(char c: wordState){
                System.out.print(c + " ");
            }

            hangmanPanel.displayWord(wordState);

            if(!wordState.contains('_')){ //word sucessfully completed
                System.out.println("You Win");
                System.out.println("The word was: " + word);
                cardPanel.showMenuPanel();
            }
        }else{
            wrongGuesses++;
            System.out.println("wrong guess");
            wrongGuessLimit();
        }


    }


    public void wrongGuessLimit(){
        if(wrongGuesses>=6){
            System.out.println("Start Over");
            System.out.println("The word was: " + word);
            cardPanel.showMenuPanel();
        }else{
        }
    }


    public void handleGuess(String letter) {
        System.out.println("The user clicked: " + letter);
        checkWord(letter);

    }


}