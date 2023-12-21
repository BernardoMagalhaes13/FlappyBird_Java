package com.flappybirdg07;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;

public class Menu {
    private TerminalScreen screen;
    private Game game;

    public Menu(TerminalScreen screen, Game game) {
        this.screen = screen;
        this.game = game;
    }
    public void showMainMenu() {
        try {
            screen.startScreen();

            drawStartButton();
            drawMessage();
            drawImage();

            KeyStroke keyStroke;
            do {
                keyStroke = screen.readInput();
            } while (keyStroke.getKeyType() != KeyType.Character || keyStroke.getCharacter() != ' ');

            game.startGame();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                screen.stopScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawStartButton() throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.BLUE);


        int centerX = (screen.getTerminalSize().getColumns() - 10) / 2;
        textGraphics.putString(centerX, 1, " [Start] ");

        screen.refresh();
    }

    private void drawImage() throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);

        int centerX = (screen.getTerminalSize().getColumns() - 5) / 2;
        textGraphics.putString(centerX, 3, "  /\\  ");
        textGraphics.putString(centerX, 4, " /  \\ ");
        textGraphics.putString(centerX, 5, "/____\\");

        screen.refresh();
    }

    private void drawMessage() throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);

        int centerX = (screen.getTerminalSize().getColumns() - 29) / 2;
        textGraphics.putString(centerX, 7, "Pressione o SPACE para jogar !");

        screen.refresh();
    }

    public static void main(String[] args) {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            Terminal terminal = terminalFactory.createTerminal();
            TerminalScreen screen = new TerminalScreen(terminal);

            Game game = new Game(screen);
            Menu menu = new Menu(screen, game);
            menu.showMainMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
