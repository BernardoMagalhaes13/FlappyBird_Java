package com.flappybirdg07;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pipes {

    public int x;
    public int y;
    public int width;
    public int height;
    public int speed = 3;

    public String orientation;

    public Pipes(String orientation) {
        this.orientation = orientation;
        reset();
    }

    public void reset() {
        width = 66;
        height = 400;
        x = Application.WIDTH + 2;

        if (orientation.equals("south")) {
            y = -(int)(Math.random() * 120) - height / 2;
        }
    }

    public void update() {
        x -= speed;
    }

    public boolean collides(int _x, int _y, int _width, int _height) {
        int margin = 2;

        if (_x + _width - margin > x && _x + margin < x + width) {
            if (orientation.equals("south") && _y < y + height) {
                return true;
            } else if (orientation.equals("north") && _y + _height > y) {
                return true;
            }
        }

        return false;
    }

    public void render(TextGraphics textGraphics) {
        char pipeChar = '#'; // Use o caractere desejado para representar o cano

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                textGraphics.setCharacter(x + j, y + i, pipeChar);
            }
        }
    }
}
