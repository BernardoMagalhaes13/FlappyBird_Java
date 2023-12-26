package com.flappybirdg07;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;

public class Menu implements Runnable {

    private Game game;
    private TerminalScreen screen;

    public Menu(int width, int height, TerminalScreen screen) {
        this.screen = screen;
        game = new Game(width, height);
        new Thread(this).start();
    }

    public void onResize(int newWidth, int newHeight) {
        // Atualiza as dimensões do jogo quando a tela é redimensionada
        game.onResize(newWidth, newHeight);
    }

    public void update() throws IOException {
        game.update();
        screen.clear();
        render(screen.newTextGraphics());
        screen.refresh();
    }

    public void render(TextGraphics textGraphics) {
        // Lógica de renderização do jogo
        // ...
    }

    public void run() {
        try {
            while (true) {
                update();
                Thread.sleep(25);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
