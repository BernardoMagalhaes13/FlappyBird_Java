package com.flappybirdg07.Draw;

import com.flappybirdg07.Game.DrawElement;
import com.flappybirdg07.Game.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
public class BirdDraw implements DrawElement {

    TextGraphics graphics;
    Position position;

    public BirdDraw(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#fcff00"));
        graphics.enableModifiers(SGR.BOLD);

        graphics.putString(new TerminalPosition(position.getX() - 2, position.getY()), "_");
        graphics.putString(new TerminalPosition(position.getX() - 1, position.getY()), "_");
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "_");

        graphics.putString(new TerminalPosition(position.getX() - 3, position.getY() + 1), "/");
        graphics.putString(new TerminalPosition(position.getX() - 2, position.getY() + 1), "_");
        graphics.putString(new TerminalPosition(position.getX() - 1, position.getY() + 1), "_");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 1), "0");
        graphics.putString(new TerminalPosition(position.getX() + 1, position.getY() + 1), "\\");

        graphics.putString(new TerminalPosition(position.getX() - 3, position.getY() + 2), "\\");
        graphics.putString(new TerminalPosition(position.getX() - 2, position.getY() + 2), "^");
        graphics.putString(new TerminalPosition(position.getX() - 1, position.getY() + 2), "^");
        graphics.putString(new TerminalPosition(position.getX(), position.getY() + 2), "_");
        graphics.putString(new TerminalPosition(position.getX() + 1, position.getY() + 2), "/");

        graphics.setForegroundColor(TextColor.Factory.fromString("#f8a515"));

        graphics.putString(new TerminalPosition(position.getX() + 2, position.getY() + 2), "-");
        graphics.putString(new TerminalPosition(position.getX() + 2, position.getY() + 1), "_");
        //graphics.setForegroundColor(TextColor.Factory.fromString("#FF1110"));
    }

    @Override
    public void setDrawParameters(Object... pars) {
        position = (Position) pars[0];
    }
}
