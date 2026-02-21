package misc;

import java.awt.*;
import javax.swing.*;
import layout.constants.HangImages;
import layout.constants.SoundManager;

public class HangSplash extends JWindow {

    public HangSplash() {
        // 1. Fresh start for the GIF
        if (HangImages.splash != null) {
            HangImages.splash.flush();
        }

        assert HangImages.splash != null;
        ImageIcon splashIcon = new ImageIcon(HangImages.splash);
        JLabel gifLabel = new JLabel(splashIcon);

        // 2. Setup UI
        JPanel content = new JPanel(new GridBagLayout());
        content.setBackground(Color.BLACK);
        content.add(gifLabel);
        this.setContentPane(content);

        // Match screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);
        this.setLocationRelativeTo(null);

        // 3. THE FIX: Set window to transparent initially
        // This prevents the "gray flash" of an unitialized window
        this.setOpacity(0.0f);

        // Ensure GIF animates on this label
        splashIcon.setImageObserver(gifLabel);
    }

    public void showSplashAndStart(Runnable onComplete) {
        // Show the window (it is still 0% opaque, so invisible to user)
        setVisible(true);

        // A very short timer to allow the AWT Event Queue to
        // finish the first paint of the black background.
        Timer readyTimer = new Timer(100, e -> {
            // Start sound
            SoundManager.playSystemSound("Windows XP Startup.wav");

            // Unveil the window now that it's "warm"
            this.setOpacity(1.0f);
            this.repaint();
        });
        readyTimer.setRepeats(false);
        readyTimer.start();

        // Main timer for the duration of the splash
        Timer exitTimer = new Timer(5100, e -> {
            setVisible(false);
            dispose();
            onComplete.run();
        });
        exitTimer.setRepeats(false);
        exitTimer.start();
    }
}