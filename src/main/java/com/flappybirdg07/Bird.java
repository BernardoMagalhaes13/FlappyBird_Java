package com.flappybirdg07;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.googlecode.lanterna.input.KeyType.Character;
import static com.googlecode.lanterna.input.KeyType.Enter;


public class Bird {

    private final Bird b;
    private Position position;
    private KeyStroke keyStroke;
    private BufferedImage image;
    private final int size = 1; // Tamanho do pássaro, ajuste conforme necessário
    private int velocity = 0;
    private final int gravity = 1;
    private boolean isGameOver = false;
    private List<Pipes> pipesList = new ArrayList<>();

    public Bird() {
        Position initialPosition = new Position(10, 10);
        b = new Bird(initialPosition);
        try {
            this.image = ImageIO.read(new File("assets/bird.gif"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleInput(KeyStroke keyStroke) {
        this.keyStroke = keyStroke;

        if (this.keyStroke.getKeyType() == Enter) {

        } else if (this.keyStroke.getKeyType() == Character) {
            char character = this.keyStroke.getCharacter();

            if (character == ' ') {
                if (!isGameOver) {
                    jump();
                }
            }
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    public void draw(TextGraphics textGraphics) {
        int width = 100;
        int height = 100;

        BufferedImage scaledImage = (BufferedImage) image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        textGraphics.drawImage(scaledImage, position.getX(), position.getY());
    }
    public Rectangle getBoundingBox() {
        return new Rectangle(
                (int) b.getPosition().getX() - b.getSize(),
                (int) b.getPosition().getY() - b.getSize() / 2,
                b.getSize() * 2,
                b.getSize());
    }
    public void generatePipes() {
        // Gerar uma posição aleatória para o cano superior
        int x = (int) (Math.random() * 80);
        int y = position.getY() + 200; // Obter a posição do pássaro

        // Gerar uma posição aleatória para o cano inferior
        int y2 = (int) (Math.random() * 80) + position.getY() + 200; // Obter a posição do pássaro

        // Verificar se os canos não se sobrepõem
        while (y2 <= y) {
            y2 = (int) (Math.random() * 80) + position.getY() + 200; // Obter a posição do pássaro
        }

        // Verificar se os canos não saem da tela
        while (y2 >= 25) {
            y2 = (int) (Math.random() * 80) + position.getY() + 200; // Obter a posição do pássaro
        }

        // Adicionar os canos à lista
        pipesList.add(new Pipes(x, y));
        pipesList.add(new Pipes(x, y2));
    }

    public void move() {
        position.setY(position.getY() + velocity + gravity);
        if (position.getY() < 0) {
            position.setY(0);
        }
        if (position.getY() >= 25) {
            position.setY(25);
        }

        Iterator<Pipes> iterator = pipesList.iterator();
        while (iterator.hasNext()) {
            Pipes pipe = iterator.next();
            pipe.move();

            // Remover canos fora da tela
            if (pipe.getPosition().getY() >= 25) {
                iterator.remove();
            }
            // Verificar colisões com canos
            if (collidesWithPipe(pipe)) {
                // Lidar com a colisão
                // ...
            }
        }
    }

    public void jump() {
        // Lógica para o salto do pássaro
        if (position.getY() > 0) {  // Garante que o pássaro não voe para fora da tela
            velocity = -2; // Ajuste conforme necessário
        }
    }

    public boolean collidesWithPipe(Pipes pipe) {
        int birdLeft = position.getX();
        int birdRight = position.getX() + getWidth();
        int birdTop = position.getY();
        int birdBottom = position.getY() + getWidth();

        int pipeLeft = pipe.getPosition().getX();
        int pipeRight = pipeLeft + pipe.getWidth();
        int pipeTop = pipe.getPosition().getY();
        int pipeBottom = pipeTop + pipe.getHeight();

        // Verificar colisão nas coordenadas x e y
        boolean xOverlap = birdLeft < pipeRight && birdRight > pipeLeft;
        boolean yOverlap = birdTop < pipeBottom && birdBottom > pipeTop;

        return xOverlap && yOverlap;
    }

    public int getWidth() {
        return size;
    }

    public int getVelocity() {
        return velocity;
    }

    public void resetGame() {
        position.setX(10);
        position.setY(10);
        velocity = 0;
        isGameOver = false;

        pipesList.clear();
    }

    public void applyGravity() {
    }
}
