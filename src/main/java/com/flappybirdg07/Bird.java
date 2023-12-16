package com.flappybirdg07;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

public class Bird {
    private Position position;
    private final int size = 1; // Tamanho do pássaro, ajuste conforme necessário
    private int velocity = 0;
    private final int gravity = 1;
    private boolean isGameOver = false;


    public Bird(Position initialPosition) {
        this.position = initialPosition;
    }
    public Position getPosition() {
        return position;
    }
    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }
    public void draw(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        textGraphics.putString(position.getX(), position.getY(), "Bird");
    }

    public void move() {
        position.setY(position.getY() + velocity + gravity);
        if (position.getY() < 0) {
            position.setY(0);
        }
        if (position.getY() >= 25) {
            position.setY(25);
        }
    }

    public void handleInput(KeyStroke keyStroke) {
        if (keyStroke.getKeyType() == KeyType.Enter) {

        } else if (keyStroke.getKeyType() == KeyType.Character && keyStroke.getCharacter() == ' ') {
            if (!isGameOver) {
                jump();
            }
        }
    }

    private void jump() {
        // Lógica para o salto do pássaro
        if (position.getY() > 0) {  // Garante que o pássaro não voe para fora da tela
            velocity = -2; // Ajuste conforme necessário
        }
    }

    public void applyGravity() {
        velocity += gravity;
    }

    public boolean collidesWith(Pipes pipe) {
        if (this.position == null) {
            return false;
        }

        int birdRight = position.getX() + size;
        int birdBottom = position.getY() + size;

        int pipeRight = pipe.getPosition().getX() + pipe.getWidth();
        int pipeBottom = pipe.getPosition().getY() + pipe.getHeight();


        return position.getX() < pipeRight &&
                birdRight > pipe.getPosition().getX() &&
                position.getY() < pipeBottom &&
                birdBottom > pipe.getPosition().getY();
    }

    public int getWidth() {
        return size;
    }

    public int getVelocity() {
        return velocity;
    }
}
