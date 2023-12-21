package com.flappybirdg07;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Pipes {

    private Position position;
    private final int width = 30;
    private final int height = 80;
    private final int speed = 1;

    public Pipes(int x, int y) {
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    public void draw(TextGraphics textGraphics) {
        // Desenhar o cano superior
        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        for (int i = 0; i < height; i++) {
            textGraphics.setCharacter(position.getX(), position.getY() + i, new TextCharacter(' '));
            textGraphics.setCharacter(position.getX() + width - 1, position.getY() + i, new TextCharacter(' '));
        }

        // Desenhar o cano inferior
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        for (int i = 0; i < height; i++) {
            textGraphics.setCharacter(position.getX(), position.getY() + height + i, new TextCharacter(' '));
            textGraphics.setCharacter(position.getX() + width - 1, position.getY() + height + i, new TextCharacter(' '));
        }
    }
    public void move() {
        position.setY(position.getY() + speed);
    }

    public boolean collidesWith(Bird bird) {
        // Verificar se o pássaro está tocando o cano superior
        if (bird.getPosition().getX() >= position.getX() &&
                bird.getPosition().getX() <= position.getX() + width &&
                bird.getPosition().getY() <= position.getY() &&
                bird.getPosition().getY() >= position.getY() - bird.getWidth()) {
            return true;
        }

        // Verificar se o pássaro está tocando o cano inferior
        if (bird.getPosition().getX() >= position.getX() &&
                bird.getPosition().getX() <= position.getX() + width &&
                bird.getPosition().getY() >= position.getY() + height &&
                bird.getPosition().getY() <= position.getY() + height + bird.getWidth()) {
            return true;
        }

        return false;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}