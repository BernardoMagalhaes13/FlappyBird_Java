package com.flappybirdg07.Draw;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.flappybirdg07.Game.*;

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

    private Score score;

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

    public void Scoreinitialize(Score score) {
        this.score = score;
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
        if (gameOverDE == null) gameOverDE = new GameOverDraw(tg, score);

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
        if (FlappyBird.getInstance().getGameState() == FlappyBird.gameState.Start) {
            drawStartScreen();
        } else {
            map.draw();
            map.getScore().draw();
        }
    }

    public void drawStartScreen() {
        tg.setBackgroundColor(TextColor.Factory.fromString("#74c3d7"));
        tg.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(map.getWidth(), map.getHeight()), ' ');

        tg.setForegroundColor(TextColor.Factory.fromString("#FFFF10"));
        tg.enableModifiers(SGR.BOLD);
        tg.putString(new TerminalPosition(map.getWidth() / 2 - 10, map.getHeight() / 2 - 5), "Press Play to Fly");
        drawButton("Start (SPACE)", new Position(map.getWidth() / 2 - 5, map.getHeight() / 2 - 3));
    }

    @Override
    public void drawGameOver() throws IOException {
        gameOver.draw();
        map.getScore().setPosition(new Position(map.getWidth() / 2 - 6, map.getHeight() - 8));
        map.getScore().draw();

        // Verifica se a tecla correspondente foi pressionada
        if (isKeyPressed()) {
            KeyStroke key = screen.pollInput();  // Apenas chama uma vez para evitar perda de eventos

            try {
                System.out.println("GameState: " + FlappyBird.getInstance().getGameState());
                System.out.println("Tecla pressionada: " + key.getCharacter()); // Adicione esta linha para depurar

                if (FlappyBird.getInstance().getGameState() == FlappyBird.gameState.GameOver) {
                    if (key.getCharacter() == 'R' || key.getCharacter() == 'r') {
                        System.out.println("Reiniciar jogo"); // Adicione esta linha para depurar
                        FlappyBird.getInstance().restartGame();
                    } else if (key.getCharacter() == 'Q' || key.getCharacter() == 'q') {
                        System.out.println("Encerrar jogo"); // Adicione esta linha para depurar
                        FlappyBird.getInstance().endGame();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        drawButton("Restart (R)", new Position(map.getWidth() / 2 - 8, map.getHeight() - 6));
        drawButton("Quit (Q)", new Position(map.getWidth() / 2 - 5, map.getHeight() - 4));
    }




    @Override
    public void endGame() {
        System.out.println("Jogo Fechado. Obrigado por jogar espero que tenha gostado e se divertido!");

        System.exit(0);
    }

    private void drawButton(String label, Position position) {
        tg.setForegroundColor(TextColor.ANSI.BLACK);
        tg.setBackgroundColor(TextColor.ANSI.WHITE);
        tg.putString(new TerminalPosition(position.getX(), position.getY()), label);
    }

    public boolean processKey(KeyStroke key) {
        switch (key.getKeyType()) {
            case Character:
                if (key.getCharacter() == ' ') {
                    return true;
                }
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public boolean isKeyPressed() throws IOException {
        KeyStroke key = screen.pollInput();

        if (key != null && (key.getKeyType() == KeyType.Character)) {
            return processKey(key);
        }
        return false;
    }

    @Override
    public void setKeyPressed(boolean keyPressed) {
    }
}
