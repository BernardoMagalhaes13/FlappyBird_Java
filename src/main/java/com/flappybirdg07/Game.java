package com.flappybirdg07;

import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;

public class Game {

    public static final int PIPE_DELAY = 100;

    private Boolean paused;

    private int pauseDelay;
    private int restartDelay;
    private int pipeDelay;
    private Bird bird;
    private ArrayList<Pipes> pipes;
    private Keyboard keyboard;

    public int score;
    public Boolean gameover;
    public Boolean started;

    public Game(int screenWidth, int screenHeight) {
        keyboard = Keyboard.getInstance();
        restart(screenWidth, screenHeight);
    }

    public void onResize(int newWidth, int newHeight) {
        // Atualizar a lógica conforme necessário para o seu jogo

        // Atualizar a largura e altura do jogo
        Application.WIDTH = newWidth;
        Application.HEIGHT = newHeight;

        // Atualizar a posição e tamanho dos elementos do jogo, por exemplo:
        bird.y = newHeight / 2;  // Centralizar a posição vertical do pássaro

        for (Pipes pipes : pipes) {
            pipes.reset();  // Redefinir a posição dos canos
        }
    }


    public void restart(int screenWidth, int screenHeight) {
        paused = false;
        started = false;
        gameover = false;

        score = 0;
        pauseDelay = 0;
        restartDelay = 0;
        pipeDelay = 0;

        bird = new Bird(screenWidth / 4, screenHeight / 2);
        pipes = new ArrayList<Pipes>();
    }

    public void update() {
        watchForStart();

        if (!started)
            return;

        watchForPause();
        watchForReset();

        if (paused)
            return;

        bird.update();

        if (gameover)
            return;

        movePipes();
        checkForCollisions();
    }

    public ArrayList<Render> getRenders() {
        ArrayList<Render> renders = new ArrayList<Render>();
        renders.add(new Render(0, 0, "java/assets/background.png")); // Assuming you have a text representation for the background
        for (Pipes pipe : pipes)
            renders.add(pipe.getRender());
        renders.add(new Render(0, 0, "java/assets/foreground.png")); // Assuming you have a text representation for the foreground
        renders.add(bird.getRender());
        return renders;
    }

    private void watchForStart() {
        if (!started && keyboard.isKeyPressed(KeyType.Character(' '))) {
            started = true;
        }
    }

    private void watchForPause() {
        if (pauseDelay > 0)
            pauseDelay--;

        if (keyboard.isKeyPressed(KeyType.Character('P')) && pauseDelay <= 0) {
            paused = !paused;
            pauseDelay = 10;
        }
    }

    private void watchForReset() {
        if (restartDelay > 0)
            restartDelay--;

        if (keyboard.isKeyPressed(KeyType.Character('R')) && restartDelay <= 0) {
            restart();
            restartDelay = 10;
        }
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
        if (bird.y + bird.height > Application.HEIGHT - 80) {
            gameover = true;
            bird.y = Application.HEIGHT - 80 - bird.height;
        }
    }
}
