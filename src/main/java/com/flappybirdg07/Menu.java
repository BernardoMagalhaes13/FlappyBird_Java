package com.flappybirdg07;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.TerminalScreen;


import java.io.IOException;
import java.util.List;

public class Menu implements Runnable {

    private Game game;
    private TerminalScreen screen;

    public Menu(int width, int height, TerminalScreen screen) {
        this.screen = screen;
        game = new Game(width, height, screen);
        new Thread(this).start();
    }

    public void onResize(int newWidth, int newHeight) {
        // Atualiza as dimensões do jogo quando a tela é redimensionada
        game.onResize(newWidth, newHeight);
    }

    public void init() {
        List<char[]> charArray = Util.loadImageAsCharArray("java/assets/background.png");
    }
    public void init(KeyStroke input) {
        // Lógica de inicialização com KeyStroke
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
    public boolean isRunning() {
        return game.started && !game.gameover; // Altere conforme necessário
    }

    public void run() {
        try {
            init(); // Chame o método init() durante a inicialização
            while (true) {
                update();
                Thread.sleep(25);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleInput(KeyStroke input) {

    }
}
