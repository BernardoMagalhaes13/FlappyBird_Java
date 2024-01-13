package com.flappybirdg07.Game;


import com.flappybirdg07.Draw.DrawFactory;

import java.io.IOException;
import java.util.Random;

public class FlappyBird {
    private static final float MAP_SPEED = 40F;
    private static final float BIRD_FALLING_SPEED = 100F;
    private static final float FIXED_PIPE_DISTANCE = 1100F;
    private static final int RANDOM_PIPE_DISTANCE = 600;
    private static final int GAME_OVER_WAITING_TIME = 1000;
    float randomTime = FIXED_PIPE_DISTANCE;

    static private FlappyBird instance = null;
    private DrawFactory adf;

    Map map = new Map(80, 24);
    GameOver gameOver = new GameOver(new Position(12, map.getHeight() / 2 - 8), map.getWidth(), map.getHeight());
    gameState gState = gameState.Start;

    private FlappyBird() {
        System.out.println("JOGO INICIADO");
        adf = new DrawFactory(map, gameOver);
    }

    public static FlappyBird getInstance() {
        if (instance == null) {
            instance = new FlappyBird();
        }
        return instance;
    }

    public static float getBirdFallingSpeed() {
        return BIRD_FALLING_SPEED;
    }

    public DrawFactory getDrawingFactory() {
        return adf;
    }
    public AbstractDrawFactory getDrawFactory() {
        return adf;
    }

    private void finishGame() throws IOException {
        adf.beginDraw();
        adf.draw();
        gState = gameState.GameOver;
        adf.drawGameOver();
        adf.endDraw();

        try {
            Thread.sleep(GAME_OVER_WAITING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void run() throws IOException {
        Random random = new Random();

        long startDraw = System.currentTimeMillis(),
                createPipe = System.currentTimeMillis(),
                moveBird = System.currentTimeMillis();

        do {
            startDraw = updateMap(startDraw);

            if (gState == gameState.Play) {
                moveBird = updateBird(moveBird);
                createPipe = updatePipes(random, createPipe);
                map.caughtSeed();
                if (map.entersOccupiedSpace()) {
                    finishGame();
                    break;
                }
            }

            if (adf.isKeyPressed()) {
                gState = gameState.Play;
                map.moveBird();
                adf.setKeyPressed(false);
            }

        } while (true);
    }

    public long updatePipes(Random random, long createPipe) {
        if (System.currentTimeMillis() - createPipe > randomTime) {
            map.computePipes();

            int rand = random.nextInt(2);
            if (rand == 0) {
                map.computeSeeds();
            }
            createPipe = System.currentTimeMillis();
            randomTime = random.nextInt(RANDOM_PIPE_DISTANCE) + FIXED_PIPE_DISTANCE;
        }
        return createPipe;
    }

    public long updateBird(long moveBird) {
        if (System.currentTimeMillis() - moveBird > BIRD_FALLING_SPEED) {
            moveBird = System.currentTimeMillis();
            map.gravityBird();
        }
        return moveBird;
    }

    public long updateMap(long startDraw) {
        if (System.currentTimeMillis() - startDraw > MAP_SPEED) {
            startDraw = System.currentTimeMillis();
            draw();
            map.moveMap();
        }
        return startDraw;
    }

    private void beginDrawing() {
        adf.beginDraw();
    }

    private void endDrawing() {
        adf.endDraw();
    }

    private void draw() {
        beginDrawing();
        adf.draw();
        endDrawing();
    }

    public gameState getGameState() {
        return gState;
    }

    public void endGame() throws IOException {
        adf.beginDraw();
        adf.draw();
        gState = gameState.GameOver;
        adf.drawGameOver();
        adf.endDraw();

        try {
            Thread.sleep(GAME_OVER_WAITING_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }

    public void restartGame() throws IOException {
        gState = gameState.Start;

        map.reset();

        // Iniciar o desenho do jogo
        adf.beginDraw();
        adf.drawStartScreen();
        adf.endDraw();
    }


    public enum gameState {
        Start,
        Play,
        GameOver
    }
}
