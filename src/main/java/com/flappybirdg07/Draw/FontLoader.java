package com.flappybirdg07.Draw;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.awt.GraphicsEnvironment;

public class FontLoader {
    private Font font;

    public FontLoader() {

        this.font = new Font("Monospaced", Font.PLAIN, 12);
    }

    public Font getFont() {
        return font;
    }

    public Font changeFont(String path, int size) {
        File fontFile = new File(path);
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(font);
            font = font.deriveFont(Font.PLAIN, size);
            return font;
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();

            return new Font("Monospaced", Font.PLAIN, size);
        }
    }
}
