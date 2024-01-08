package com.flappybirdg07.Draw;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.flappybirdg07.Game.DrawElement;
import com.flappybirdg07.Game.Position;

public class PipeDraw implements DrawElement {
    TextGraphics graphics;
    Position position;
    private int height;
    private int width;
    private int option;

    public PipeDraw(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#78b029"));
        graphics.enableModifiers(SGR.BOLD);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++)
                graphics.putString(new TerminalPosition(position.getX() + j, position.getY() + i * option), "█");
        }
    }

    @Override
    public void setDrawParameters(Object... pars) {
        position = (Position) pars[0];
        height = (int) pars[1];
        width = (int) pars[2];
        option = (int) pars[3];
    }
}
