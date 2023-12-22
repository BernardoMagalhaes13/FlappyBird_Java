package java.com.flappybirdg07;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.Key;
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

            GamePanel panel = new GamePanel(WIDTH, HEIGHT, screen);

            screen.getTerminal().addResizeListener((terminal, newSize) -> {
                panel.onResize(newSize.getColumns(), newSize.getRows());
            });

            screen.getTerminal().addInputListener((input) -> {
                if (input.getKeyType() == KeyType.Escape) {
                    try {
                        screen.stopScreen();
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            screen.addResizeListener((event) -> {
                panel.onResize(event.getNewSize().getColumns(), event.getNewSize().getRows());
            });

            screen.addInputListener((input) -> {
                if (input.getKeyType() == KeyType.Escape) {
                    try {
                        screen.stopScreen();
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    keyboard.handleInput(input);
                }
            });

            panel.init();

            while (true) {
                panel.update();
                panel.render();
                screen.refresh();
                Thread.sleep(100);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
