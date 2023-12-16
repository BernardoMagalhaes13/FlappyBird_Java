package com.flappybirdg07;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pipes {
    private Position position;
    private final int width = 30;
    private final int height = 80;
    private final int speed = 1;

    public Pipes(Position initialPosition) {
        this.position = initialPosition;
    }

    public Position getPosition() {
        return position;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    // Move o cano para a esquerda na velocidade especificada
    public void move() {
        position.setX(position.getX() - speed);
    }

    // Desenha o cano na tela
    public void draw(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);

        // Desenha cada parte do cano
        for (int y = 0; y < height; y++) {
            textGraphics.setCharacter(position.getX(), position.getY() + y, new TextCharacter('|'));
        }
    }
}
