package com.flappybirdg07;

import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Application {

    public static int WIDTH = 50;
    public static int HEIGHT = 20;

    public static void main(String[] args) {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            TerminalScreen screen = terminalFactory.createScreen();

            screen.startScreen();
            screen.doResizeIfNecessary();

            Keyboard keyboard = Keyboard.getInstance();

            // Create separate objects for menu and game
            Menu menu = new Menu(screen);
            Game game = new Game(WIDTH, HEIGHT);

            // Handle resizing for both menu and game
            screen.addResizeListener((terminal, newSize) -> {
                menu.onResize(newSize.getColumns(), newSize.getRows());
                game.onResize(newSize.getColumns(), newSize.getRows());
            });

            // Handle input for both menu and game
            screen.addInputListener((input) -> {
                if (input.getKeyType() == KeyType.Escape) {
                    try {
                        screen.stopScreen();
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Handle menu input
                    if (menu.isRunning()) {
                        menu.handleInput(input);
                    } else {
                        // Handle game input
                        game.handleInput(input);
                    }
                }
            });

            // Initialize and start both menu and game
            menu.init();
            game.init();

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
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}