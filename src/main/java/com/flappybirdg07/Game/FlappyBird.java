package com.flappybirdg07.Game;

import com.flappybirdg07.Draw.DrawFactory;

public class FlappyBird {
    private static final float MAP_SPEED = 40F;
    private static final float BIRD_FALLING_SPEED = 100F;
    private static final float FIXED_PIPE_DISTANCE = 1100F;
    private static final int RANDOM_PIPE_DISTANCE = 600;
    private static final int GAME_OVER_WAITING_TIME = 1000;
    float randomTime = FIXED_PIPE_DISTANCE;

    static private String factory = null;
    static private FlappyBird instance = null;
    AbstractDrawFactory adf;

    Map map = new Map(80, 24);
    GameOver gameOver = new GameOver(new Position(12, map.getHeight() / 2 - 8), map.getWidth(), map.getHeight());
    gameState gState = gameState.Start;
