package layout.constants;

import layout.design.Design;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class HangImages {
    // Inside your HangImages class:
    public static final URL splashUrl;
    public static final Image splash;
    public static Image background;
    public static Image windows;
    public static Image shutdown;

    public static Image startIcon;
    public static Image htpIcon;
    public static Image devsIcon;
    public static Image licenseIcon;

    static {
        try {
            splashUrl = Design.class.getResource("/images/splash.gif");
            splash = Toolkit.getDefaultToolkit().createImage(splashUrl);
            background = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/wallpaper.png")));
            windows = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/windows.png")));
            shutdown = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/shutdown.png")));

            licenseIcon = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/lcns_icon.png")));
            devsIcon = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/devs_icon.png")));
            htpIcon = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/htp_icon.png")));
            startIcon = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/start_icon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


