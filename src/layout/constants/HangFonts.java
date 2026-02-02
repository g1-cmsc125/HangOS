package layout.constants;

import java.awt.Font;

public class HangFonts {
    // Can also add here integer for Heading fonts, regular fonts, etc.
    public static final String DEFAULT_FAMILY = "Arial";

    // 1. Change 'void' to 'Font' so it returns the object
    // 2. Accept 'int style' (not a Font object) and 'int size'
    public static Font loadCustomFonts(int style, int size) {
        return new Font(DEFAULT_FAMILY, style, size);
    }
}
