package com.flappybirdg07.Draw;

import com.flappybirdg07.Game.Score;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.flappybirdg07.Game.DrawElement;
import com.flappybirdg07.Game.Position;

public class GameOverDraw implements DrawElement {
    TextGraphics graphics;
    Position position;
    int height;
    int width;
    Score score;
    public GameOverDraw(TextGraphics g, Score score) {
        graphics = g;
        this.score = score;
    }
    @Override
    public void draw() {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#74c3d7"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF10"));
        graphics.enableModifiers(SGR.BOLD);

        String[] gameOverText = {
             "GAME OVER"
        };

        int startY = position.getY();
        for (int i = 0; i < gameOverText.length; i++) {
            graphics.putString(new TerminalPosition(position.getX(), startY + i), gameOverText[i]);
        }
    }

    @Override
    public void setDrawParameters(Object... pars) {
        position = (Position) pars[0];
        width = (int) pars[1];
        height = (int) pars[2];
    }
}
