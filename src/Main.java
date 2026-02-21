import layout.Card;
import misc.HangSplash;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure Thread safety
        SwingUtilities.invokeLater(() -> {
            HangSplash splash = new HangSplash();

            // Run the splash, then provide the code to run when finished
            splash.showSplashAndStart(() -> {
                Card app = new Card();
                app.setVisible(true);
            });
        });
    }
}