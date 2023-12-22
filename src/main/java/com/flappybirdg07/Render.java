package com.flappybirdg07;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.image.Image;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import java.io.IOException;

public class Render {

    public int x;
    public int y;
    public char[][] image;

    public Render() {
    }

    public Render(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.image = Util.loadImageAsCharArray(imagePath);
    }

    public void render(TextGraphics textGraphics) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                textGraphics.setCharacter(x + j, y + i, new TextCharacter(image[i][j]));
            }
        }
    }
}
