package layout;

import layout.design.Design;
import layout.hangOS_interfaces.Start;
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
        this.setUndecorated(true);
        this.setLayout(new BorderLayout());

        addPanels();
        this.add(container, BorderLayout.CENTER);

        Design.footerDesign(this);

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

        Start start = new Start();
        container.add(start, "Start");

        // Starting screen
        cardLayout.show(container, "Main Menu");
    }

    public static void screenChoice(String cardPage) {
        System.out.println("Trying to switch from " + currentPage + " to " + cardPage);

        if(cardPage == null || cardPage.isEmpty() || cardPage.equals(currentPage)) {
            return; // return early
        }
        cardLayout.show(container, cardPage);
    }
}