package layout.constants;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;


// This is a new class to load the Windows Tahoma font
public class LoadTahoma {
    public static int regularFontSize = 20;
    public static int titleFontSize = 45;
    public static int subTitleFontSize = 32;

    private static Font customFontBase;

    static {
        // Font uploaded on resources/font
        String fontPath = "/font/windows-xp-tahoma.otf";

        // To check if font exists
        try (InputStream is = LoadTahoma.class.getResourceAsStream(fontPath)) {
            if (is == null) {
                throw new IOException("Font file not found at: " + fontPath);
            }
            // Now create the font and register to the envi
            customFontBase = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFontBase);
        } catch (IOException | FontFormatException e) {
            System.err.println("Could not load font, falling back to Arial. Error: " + e.getMessage());
            customFontBase = new Font("Arial", Font.PLAIN, 12);
        }
    }

    // Load Font method
    public static Font loadCustomFonts(int style, int size) {
        return customFontBase.deriveFont(style, (float) size);
    }
}