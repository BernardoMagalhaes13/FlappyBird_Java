package com.flappybirdg07.Draw;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.flappybirdg07.Game.DrawElement;
import com.flappybirdg07.Game.Position;

public class BackGroundDraw implements DrawElement {
    TextGraphics graphics;
    Position position;
    int height;
    int width;

    public BackGroundDraw(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#71c5cf"));
        graphics.fillRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(width, height), ' ');
    }

    @Override
    public void setDrawParameters(Object... pars) {
        position = (Position) pars[0];
        width = (int) pars[1];
        height = (int) pars[2];
    }
}

