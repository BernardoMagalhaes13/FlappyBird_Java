package com.flappybirdg07.Draw;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.awt.GraphicsEnvironment;

public class FontConfig {
    private static Font font = new Font("Monospaced", Font.PLAIN, 12);

    public static Font getFont() {
        return font;
    }

    public static void setFont(Font newFont) {
        font = newFont;
    }

    public static Font changeFont(String path, int size) {
        File fontFile = new File(path);
        try {
            Font loadedFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(loadedFont);
            return loadedFont.deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return new Font("Monospaced", Font.PLAIN, size);
        }
    }
}
