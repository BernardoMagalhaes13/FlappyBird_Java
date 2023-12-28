package com.flappybirdg07;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Bird {

    public int x;
    public int y;
    public int width;
    public int height;

    public boolean dead;

    public double yvel;
    public double gravity;

    private int jumpDelay;
    private double rotation;

    private Keyboard keyboard;
    private BufferedImage birdImage;

    public Bird(int initialX, int initialY) {
        x = initialX;
        y = initialY;
        yvel = 0;
        width = 3; // Largura padrão
        height = 2; // Altura padrão
        gravity = 0.5;
        jumpDelay = 0;
        rotation = 0.0;
        dead = false;

        keyboard = Keyboard.getInstance();

        // Carregar a imagem do pássaro
        try {
            birdImage = ImageIO.read(new File("java/assets/bird.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(KeyStroke input) {
        yvel += gravity;

        if (jumpDelay > 0)
            jumpDelay--;

        if (!dead && input != null && input.getCharacter() == ' ' && jumpDelay <= 0) {
            yvel = -3; // Ajuste o valor conforme necessário
            jumpDelay = 10;
        }

        y += (int) yvel;
    }

    public Render getRender() {
        return new Render(x, y, "java/assets/bird.png");

    }
}
