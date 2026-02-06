package layout;

import layout.design.Design;
import layout.hangOS_interfaces.HTP;
import layout.hangOS_interfaces.MainMenu;

import java.awt.*;

// swing components
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Card extends JFrame {
    // static SoundUtils sound = new SoundUtils();
    static CardLayout cardLayout = new CardLayout();
    static JPanel container = new JPanel(cardLayout);

    public static String currentPage = "";

    public Card() {
        this.setTitle("HangOS");
        this.setSize(Design.screenWidth, Design.screenHeight);
        this.setResizable(false);

        addPanels();

        this.add(container);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private static void addPanels() {
        // Panels
        // Note: first added - first one to shows
        MainMenu mainMenu = new MainMenu();
        container.add(mainMenu, "Main Menu");

        HTP htp = new HTP();
        container.add(htp, "How To Play");
        /*
        LoadScreen loadScreen = new LoadScreen();
        container.add(loadScreen, "Load Screen");

        MainMenu mainMenu = new MainMenu();
        container.add(mainMenu, "Main Menu");

        Play play = new Play();
        container.add(play, "Play");

        HowToPlay howToPlay = new HowToPlay();
        container.add(howToPlay, "How To Play");

        Settings settings = new Settings();
        container.add(settings, "Settings");

        Surrender surrender = new Surrender();
        container.add(surrender, "Surrender");

        cardLayout.show(container, "Load Screen");
        SoundUtils.bgMusic();
         */
        cardLayout.show(container, "How To Play");
    }

    public static void screenChoice(String cardPage) {
        System.out.println("Trying to switch from " + currentPage + " to " + cardPage);

        if(cardPage == null || cardPage.isEmpty() || cardPage.equals(currentPage)) {
            return; // return early
        }
        cardLayout.show(container, cardPage);
    }
}