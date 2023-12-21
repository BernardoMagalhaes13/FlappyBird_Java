package com.flappybirdg07;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Game implements Runnable {

    private Bird b;
    private LinkedList<Pipes> pipe = new LinkedList<>();
    private Random random = new Random();
    private Score score;

    private TerminalScreen screen;
    private boolean jogoEmExecucao = true;

    public Game(TerminalScreen screen) {
        this.screen = screen;
        adder(new Pipes(40, getRandomPipeHeight()));
        score = new Score();
    }

    public void adder(Pipes p) {
        pipe.add(p);
    }

    private void draw() throws IOException {
        TextGraphics textGraphics = screen.newTextGraphics();

        // Desenha o pássaro
        drawBird(textGraphics);

        // Desenha os canos
        Iterator<Pipes> iterator = pipe.iterator();
        while (iterator.hasNext()) {
            Pipes tempPipe = iterator.next();
            drawPipe(textGraphics, tempPipe);

            // Adicione a lógica para remover canos fora da tela
            if (tempPipe.getPosition().getX() < 0) {
                iterator.remove();
            }
        }

        // Exibe a pontuação
        textGraphics.putString(2, 1, "Score: " + score.getScore());

        // Atualiza a tela
        screen.refresh();
    }

    private void runGameLoop() {
        try {
            while (jogoEmExecucao) {
                for (Pipes tempPipe : pipe) {
                    tempPipe.move();
                }

                checkCollisions();

                if (random.nextInt(100) < 5) {
                    adder(new Pipes(40, getRandomPipeHeight()));

                }

                b.move();
                draw();
                Thread.sleep(10);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                screen.stopScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawBird(TextGraphics textGraphics) {
        textGraphics.setForegroundColor(TextColor.ANSI.WHITE);
        textGraphics.setBackgroundColor(TextColor.ANSI.DEFAULT);
        b.draw(textGraphics);
    }

    private void drawPipe(TextGraphics textGraphics, Pipes pipe) {
        pipe.draw(textGraphics);
    }

    private void handleEndGame() {
        System.out.println("Fim do jogo! Pontuação final: " + score.getScore());
    }

    public void checkCollisions() {
        for (Pipes tempPipe : pipe) {
            if (b.collidesWithPipe(tempPipe)) {
                handleEndGame();
                return;
            }
        }

        if (b.getPosition().getY() <= 0 || b.getPosition().getY() >= screen.getTerminalSize().getRows()) {
            handleEndGame();
        }
    }

    public boolean collidesWithPipe(Pipes pipe) {
        // Get the bird's bounding box
        Rectangle birdBoundingBox = b.getBoundingBox();

        // Get the pipe's bounding box
        Rectangle pipeBoundingBox = pipe.getBoundingBox();

        // Check if the bounding boxes intersect
        return birdBoundingBox.intersects(pipeBoundingBox);
    }

    private void endGame() {
        System.out.println("Fim do jogo! Pontuação final: " + score.getScore());
        System.out.println("Pressione a barra de espaço para reiniciar o jogo.");

        try {
            KeyStroke keyStroke;
            do {
                keyStroke = screen.readInput();
            } while (keyStroke == null || keyStroke.getKeyType() != KeyType.Character || keyStroke.getCharacter() != ' ');
        } catch (IOException e) {
            e.printStackTrace();
        }
        restartGame();
    }

    private void restartGame() {
        b.setPosition(new Position(10, 10));
        pipe.clear();
        adder(new Pipes(40, getRandomPipeHeight()));
        score.resetScore();
        jogoEmExecucao = true;
    }

    @Override
    public void run() {
        try {
            while (jogoEmExecucao) {
                for (Pipes tempPipe : pipe) {
                    tempPipe.move();
                }

                checkCollisions();

                if (random.nextInt(100) < 5) {
                    adder(new Pipes(40, getRandomPipeHeight()));
                }

                b.move();
                draw();
                Thread.sleep(10);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                screen.stopScreen();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getRandomPipeHeight() {
        return random.nextInt(10) + 5;
    }

    public static void main(String[] args) {
        try {
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
            Terminal terminal = terminalFactory.createTerminal();
            TerminalScreen screen = new TerminalScreen(terminal);
            screen.startScreen();

            Game game = new Game(screen);
            game.startMenu();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startMenu() {
        Menu menu = new Menu(screen, this);
        menu.showMainMenu();
    }

    public void startGame() {
        Position initialPosition = new Position(10, 10);
        b = new Bird();

        setBird(b);

        Thread gameThread = new Thread(this);
        gameThread.start();

        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setBird(Bird bird) {
        this.b = bird;
    }
}
