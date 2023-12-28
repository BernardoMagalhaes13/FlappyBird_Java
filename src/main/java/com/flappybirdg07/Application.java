package com.flappybirdg07;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Application {

    public static int WIDTH = 50;
    public static int HEIGHT = 20;

    public static void main(String[] args) {
        TerminalScreen screen = null;

        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            screen = terminalFactory.createScreen();

            screen.startScreen();
            screen.doResizeIfNecessary();

            Keyboard keyboard = Keyboard.getInstance();

            // Create separate objects for menu and game
            Menu menu = new Menu(WIDTH, HEIGHT, screen);
            Game game = new Game(WIDTH, HEIGHT, screen);

            // Handle resizing for both menu and game
            screen.getTerminal().addResizeListener((terminal, newSize) -> {
                menu.onResize(newSize.getColumns(), newSize.getRows());
                game.onResize(newSize.getColumns(), newSize.getRows());
            });

            // Initialize and start both menu and game
            menu.init();
            game.init(WIDTH, HEIGHT);

            // Switch between menu and game states
            boolean isMenuActive = true;
            while (true) {
                if (isMenuActive) {
                    // Render and update menu
                    menu.render(screen.newTextGraphics());
                    menu.update();
                } else {
                    // Render and update game
                    game.render(screen.newTextGraphics());
                    game.update();
                }

                screen.refresh();
                Thread.sleep(100);

                // Switch between menu and game
                isMenuActive = !isMenuActive;

                // Read input
                KeyStroke input = screen.pollInput();
                if (input != null) {
                    if (input.getKeyType() == KeyType.Escape) {
                        try {
                            screen.stopScreen();
                            System.exit(0);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        // Handle menu or game input
                        if (isMenuActive) {
                            menu.handleInput(input);
                        } else {
                            game.handleInput(input);
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Certifique-se de fechar o TerminalScreen quando terminar
            if (screen != null) {
                try {
                    screen.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
