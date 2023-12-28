package com.flappybirdg07;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int PIPE_DELAY = 100;

    private Boolean paused;
    private int pauseDelay;
    private int restartDelay;
    private int pipeDelay;
    private Bird bird;
    private ArrayList<Pipes> pipes;
    private Keyboard keyboard;
    private TerminalScreen screen;

    private int screenWidth;
    private int screenHeight;

    public int score;
    public Boolean gameover;
    public Boolean started;

    public void render(TextGraphics textGraphics) {
        for (Render render : getRenders()) {
            int x = render.x;
            int y = render.y;
            List<char[]> image = render.image;

            for (char[] lineChars : image) {
                String line = new String(lineChars);
                textGraphics.putString(x, y, line);
                y++;
            }
        }
    }

    public Game(int screenWidth, int screenHeight, TerminalScreen screen) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.screen = screen;

        keyboard = Keyboard.getInstance();
        init(screenWidth, screenHeight);
    }

    public void init(int WIDTH, int HEIGHT) {
        paused = false;
        started = false;
        gameover = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;

        bird = new Bird(screenWidth / 4, screenHeight / 2);
        pipes = new ArrayList<>();
        List<char[]> charArray = Util.loadImageAsCharArray("java/assets/background.png");

    }

    public void init(KeyStroke input) {
        // Lógica de inicialização com KeyStroke
    }

    public void onResize(int newWidth, int newHeight) {
        // Atualizar a lógica conforme necessário para o seu jogo

        // Atualizar a largura e altura do jogo
        // (Aqui você pode decidir se deseja ajustar as posições dos elementos ao redimensionar)
        bird.y = newHeight / 2;  // Centralizar a posição vertical do pássaro
        for (Pipes pipe : pipes) {
            pipe.reset();  // Redefinir a posição dos canos
        }
    }

    public void restart() {
        init(screenWidth, screenHeight);
    }

    public void update() throws IOException {
        onResize(screen.getTerminalSize().getColumns(), screen.getTerminalSize().getRows());

        watchForStart();

        if (!started) {
            return;
        }

        watchForPause();
        watchForReset();

        if (paused) {
            return;
        }

        KeyStroke input = screen.pollInput();

        if (input != null) {
            bird.update(input);
            handleInput(input);
        }

        if (gameover) {
            return;
        }

        movePipes();
        checkForCollisions();
    }
    private void togglePause() {
        paused = !paused;
        pauseDelay = 10;
    }
    private void watchForStart() {
        if (!started && keyboard.isKeyPressed(' ')) {
            started = true;
        }
    }
    private void watchForReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isKeyPressed('R') && restartDelay <= 0) {
            restart();
            restartDelay = 10;
        }
    }

    private void watchForPause() {
        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isKeyPressed('P') && (pauseDelay <= 0)) {
            togglePause();
        }
    }

    public ArrayList<Render> getRenders() {
        ArrayList<Render> renders = new ArrayList<>();
        renders.add(new Render(0, 0, "java/assets/background.png")); // Assuming you have a text representation for the background
        for (Pipes pipe : pipes)
            renders.add(pipe.getRender());
        renders.add(new Render(0, 0, "java/assets/foreground.png")); // Assuming you have a text representation for the foreground
        renders.add(bird.getRender());
        return renders;
    }

    private void movePipes() {
        pipeDelay--;

        if (pipeDelay < 0) {
            pipeDelay = PIPE_DELAY;
            Pipes northPipe = null;
            Pipes southPipe = null;

            // Look for pipes off the screen
            for (Pipes pipe : pipes) {
                if (pipe.x - pipe.width < 0) {
                    if (northPipe == null) {
                        northPipe = pipe;
                    } else if (southPipe == null) {
                        southPipe = pipe;
                        break;
                    }
                }
            }

            if (northPipe == null) {
                Pipes pipe = new Pipes("north");
                pipes.add(pipe);
                northPipe = pipe;
            } else {
                northPipe.reset();
            }

            if (southPipe == null) {
                Pipes pipe = new Pipes("south");
                pipes.add(pipe);
                southPipe = pipe;
            } else {
                southPipe.reset();
            }

            northPipe.y = southPipe.y + southPipe.height + 175;
        }

        for (Pipes pipe : pipes) {
            pipe.update();
        }
    }

    private void checkForCollisions() {

        for (Pipes pipes : pipes) {
            if (pipes.collides(bird.x, bird.y, bird.width, bird.height)) {
                gameover = true;
                bird.dead = true;
            } else if (pipes.x == bird.x && pipes.orientation.equalsIgnoreCase("south")) {
                score++;
            }
        }

        // Ground + Bird collision
        if (bird.y + bird.height > screenHeight - 80) {
            gameover = true;
            bird.y = screenHeight - 80 - bird.height;
        }
    }

    public void handleInput(KeyStroke input) {

    }
}
