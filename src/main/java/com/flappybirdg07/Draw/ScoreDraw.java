package com.flappybirdg07.Draw;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.flappybirdg07.Game.DrawElement;
import com.flappybirdg07.Game.Position;

public class ScoreDraw implements DrawElement {

    TextGraphics graphics;
    Position position;
    private int scoreTracker;

    public ScoreDraw(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF1110"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "Score: " + scoreTracker + " ");
    }

    @Override
    public void setDrawParameters(Object... pars) {
        position = (Position) pars[0];
        scoreTracker = (int) pars[1];
    }
}
