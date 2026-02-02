package layout.constants;

import java.awt.Font;

public class HangFonts {
    // Private constructor to prevent instantiation
    private HangFonts() {
        throw new AssertionError("Cannot instantiate HangFonts");
    }

    // Windows XP fonts
    public static final Font START_BUTTON = new Font("Arial", Font.BOLD | Font.ITALIC, 24);
    public static final Font TASKBAR_TIME = new Font("Tahoma", Font.BOLD, 13);
}
