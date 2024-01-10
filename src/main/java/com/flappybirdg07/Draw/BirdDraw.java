package com.flappybirdg07.Draw;

import com.flappybirdg07.Game.DrawElement;
import com.flappybirdg07.Game.Position;
import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.awt.Font;

public class BirdDraw implements DrawElement {

    private TextGraphics graphics;
    private Position position;

    public BirdDraw(TextGraphics g) {
        graphics = g;
    }

    @Override
    public void draw() {
        Font currentFont = FontConfig.getFont();

        graphics.setForegroundColor(TextColor.Factory.fromString("#fcff00"));
        graphics.enableModifiers(SGR.BOLD);

        String text = "123456789";
        int x = position.getX() - 2;
        int y = position.getY();

        for (char c : text.toCharArray()) {
            graphics.putString(x, y, String.valueOf(c));
            x++;
        }
    }

    @Override
    public void setDrawParameters(Object... pars) {
        position = (Position) pars[0];
    }
}
