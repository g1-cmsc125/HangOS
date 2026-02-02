package layout.constants;

import layout.design.Design;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class HangImages {
    public static Image background;
    public static Image windows;
    static {
        try {
            background = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/wallpaper.png")));
            windows = ImageIO.read(Objects.requireNonNull(Design.class.getResource("/images/windows.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


