package com.flappybirdg07;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Menu implements Runnable {

    private Game game;
    private TerminalScreen screen;

    public Menu(TerminalScreen screen) {
        this.screen = screen;
        game = new Game(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows());
        new Thread(this).start();
    }

    public void update() {
        game.update();
        screen.clear();
        render(screen.newTextGraphics());
        screen.refresh();
    }

    public void render(TextGraphics textGraphics) {
        for (Render r : game.getRenders()) {
            int x = r.x;
            int y = r.y;

            if (r.transform != null) {
                // You may need to implement a custom method to draw transformed images
                drawTransformed(textGraphics, r);
            } else {
                // Assuming r.image is a 2D char array
                for (char[] row : r.image) {
                    for (char pixel : row) {
                        textGraphics.setCharacter(new TerminalPosition(x++, y), new TextCharacter(pixel));
                    }
                    x = r.x; // Reset x for the next row
                    y++;
                }
            }
        }

        textGraphics.setForegroundColor(TextColor.ANSI.BLACK);

        if (!game.started) {
            textGraphics.putString(10, 15, "Press SPACE to start");
        } else {
            textGraphics.putString(2, 20, "Score: " + game.score);
        }

        if (game.gameover) {
            textGraphics.putString(10, 15, "Press R to restart");
        }
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
