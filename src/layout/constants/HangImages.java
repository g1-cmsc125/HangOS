package layout.constants;

import layout.design.Design;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class HangImages {
    public static Image background;
    public static Image windows;
    public static Image startIcon;
    public static Image htpIcon;
    static {
        try {
            background = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/wallpaper.png")));
            windows = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/windows.png")));
            startIcon = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/start_icon.png")));
            htpIcon = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/htp_icon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


