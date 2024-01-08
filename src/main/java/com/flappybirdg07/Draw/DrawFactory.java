package com.flappybirdg07.Draw;

import com.flappybirdg07.Game.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class DrawFactory implements AbstractDrawFactory {

    TextGraphics tg;
    Map map;
    GameOver gameOver;
    DrawElement birdDE = null;
    DrawElement pipeDE = null;
    DrawElement limitDE = null;
    DrawElement scoreDE = null;
    DrawElement gameOverDE = null;
    DrawElement backGroundDE = null;
    DrawElement seedDE = null;
    private Screen screen;

    public DrawFactory(Map m, GameOver go) {
        map = m;
        gameOver = go;
        Terminal terminal = null;
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
            tg = screen.newTextGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DrawElement getBirdDrawElement() {
        if (birdDE == null) birdDE = new BirdDraw(tg);

        return birdDE;
    }

    @Override
    public DrawElement getPipeDrawElement() {
        if (pipeDE == null) pipeDE = new PipeDraw(tg);

        return pipeDE;
    }

    @Override
    public DrawElement getLimitDrawElement() {
        if (limitDE == null) limitDE = new LimitDraw(tg);

        return limitDE;
    }

    @Override
    public DrawElement getGameOverDrawElement() {
        if (gameOverDE == null) gameOverDE = new GameOverDraw(tg);

        return gameOverDE;
    }

    @Override
    public DrawElement getScoreDrawElement() {
        if (scoreDE == null) scoreDE = new ScoreDraw(tg);

        return scoreDE;
    }

    @Override
    public DrawElement getBackgroundDrawElement() {
        if (backGroundDE == null) backGroundDE = new BackGroundDraw(tg);

        return backGroundDE;
    }

    @Override
    public DrawElement getSeedDrawElement() {
        if (seedDE == null) seedDE = new SeedDraw(tg);

        return seedDE;
    }

    @Override
    public void beginDraw() {
        screen.clear();
    }

    @Override
    public void endDraw() {
        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        map.draw();
        map.getScore().draw();
    }

    @Override
    public void drawGameOver() {
        gameOver.draw();
        map.getScore().setPosition(new Position(map.getWidth() / 2 - 6, map.getHeight() - 8));
        map.getScore().draw();
    }

    @Override
    public void endGame() {
        try {
            screen.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case Enter:
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean isKeyPressed() throws IOException {
        KeyStroke key;
        key = screen.pollInput();

        if (key != null && (key.getKeyType() == KeyType.Escape || key.getKeyType() == KeyType.EOF)) {
            endGame();
            return true;
        } else if (key != null) {
            return processKey(key);
        }
        return false;
    }

    @Override
    public void setKeyPressed(boolean keyPressed) {
    }
}
