package com.flappybirdg07.Draw;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.flappybirdg07.Game.DrawElement;
import com.flappybirdg07.Game.Position;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SeedDraw implements DrawElement {

    TextGraphics graphics;
    Position position;
    private Font font;
    public SeedDraw(TextGraphics g) {
        graphics = g;
    }
    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }


    public Font changeFont(String path, int size){
        File fontFile = new File(path);
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,fontFile);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);
        Font loaded = font.deriveFont(Font.PLAIN,size);
        return loaded;
    }
    @Override
    public void draw() {
        graphics.setForegroundColor(TextColor.Factory.fromString("#632d00"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "o");
    }

    @Override
    public void setDrawParameters(Object... pars) {
        position = (Position) pars[0];
    }
}
