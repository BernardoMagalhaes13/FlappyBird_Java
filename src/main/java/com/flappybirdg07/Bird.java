package com.flappybirdg07;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyType;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.googlecode.lanterna.input.KeyType.*;

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

    public void update() {
        yvel += gravity;

        if (jumpDelay > 0)
            jumpDelay--;

        if (!dead && keyboard.isKeyPressed(KeyType.Character(' ')) && jumpDelay <= 0) {
            yvel = -3; // Ajuste o valor conforme necessário
            jumpDelay = 10;
        }

        y += (int) yvel;
    }

    public void render(TextGraphics textGraphics) {
        if (birdImage != null) {
            // Renderizar a imagem do pássaro
            for (int i = 0; i < birdImage.getHeight(); i++) {
                for (int j = 0; j < birdImage.getWidth(); j++) {
                    char character = ' '; // Use um caractere adequado
                    int color = birdImage.getRGB(j, i);
                    textGraphics.setForegroundColor(TextColor.ANSI.DEFAULT); // Defina a cor para padrão

                    // Verifique se a cor é diferente de branco
                    if ((color & 0xFFFFFF) != 0xFFFFFF) {
                        textGraphics.setCharacter(x + j, y + i, character);
                    }
                }
            }
        } else {
            // Se a imagem não for carregada, renderize um caractere de texto
            char birdChar = 'O'; // Use o caractere desejado para representar o pássaro
            textGraphics.setForegroundColor(TextColor.ANSI.YELLOW); // Ajuste a cor conforme necessário

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    textGraphics.setCharacter(x + j, y + i, birdChar);
                }
            }
        }
    }
}
