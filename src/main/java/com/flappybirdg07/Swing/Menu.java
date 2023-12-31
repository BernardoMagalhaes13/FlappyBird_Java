package com.flappybirdg07.Swing;
import com.flappybirdg07.Swing.Game;
import com.flappybirdg07.Swing.Keyboard;
import com.flappybirdg07.Swing.Render;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;
public class Menu extends JPanel implements Runnable {

    private Game game;
    private boolean showEndGameScreen = false;
    private Keyboard keyboard;
    private int currentScore = 0;
    private int bestScore = 0;
    private JButton restartButton;
    private JButton closeButton;
    private Preferences preferences;


    public Menu() {
        game = new Game();
        keyboard = Keyboard.getInstance();
        preferences = Preferences.userNodeForPackage(Menu.class);
        bestScore = preferences.getInt("bestScore", 0);

        new Thread(this).start();
        setFocusable(true);
        addKeyListener(keyboard);

        // Inicializa os botões apenas uma vez
        restartButton = new JButton("Restart");
        closeButton = new JButton("Close");

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartGame();
            }
        });

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(restartButton);
        add(closeButton);

        setLayout(null);
        restartButton.setBounds(150, 380, 100, 40);
        closeButton.setBounds(300, 380, 100, 40);
    }

    public void update() {
        if (!showEndGameScreen) {
            game.update();
            checkInput();
            repaint();
        }
    }

    private void checkInput() {
        if (keyboard.isDown(KeyEvent.VK_SPACE)) {
            if (!game.started) {
                game.started = true;
            } else if (game.gameover) {
                restartGame();
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!showEndGameScreen) {
            Graphics2D g2D = (Graphics2D) g;

            for (Render r : game.getRenders())
                if (r.transform != null)
                    g2D.drawImage(r.image, r.transform, null);
                else
                    g.drawImage(r.image, r.x, r.y, null);

            g2D.setColor(Color.BLACK);

            if (!game.started) {
                g2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                g2D.drawString("Press SPACE to start", 150, 240);
            } else {
                g2D.setFont(new Font("TimesRoman", Font.PLAIN, 24));

                // Exibe o score da jogada
                String scoreText = "Score: " + currentScore;
                g2D.drawString(scoreText, 10, 30);
            }
        }

        if (game.gameover && !showEndGameScreen) {
            showEndGameScreen = true;
            drawEndGameScreen(g);
        }
    }

    private void drawEndGameScreen(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;

        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, 30));

        String scoreText = "Your Score: " + currentScore;
        g2D.drawString(scoreText, 150, 300);

        String bestScoreText = "Best Score: " + bestScore;
        g2D.drawString(bestScoreText, 150, 340);

        restartButton.setVisible(true);
        closeButton.setVisible(true);

        if (currentScore > bestScore) {
            bestScore = currentScore;
            preferences.putInt("bestScore", bestScore); // Salva o melhor score no armazenamento
        }
    }

    private void restartGame() {
        showEndGameScreen = false;
        game = new Game();
        currentScore = 0;

        restartButton.setVisible(false);
        closeButton.setVisible(false);

        repaint();
    }

    public void run() {
        try {
            while (true) {
                if (!showEndGameScreen) {
                    currentScore = game.score;
                }
                update();
                Thread.sleep(25);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
