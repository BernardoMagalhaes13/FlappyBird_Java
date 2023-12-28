package com.flappybirdg07;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.graphics.TextGraphics;
import java.awt.geom.AffineTransform;
import java.util.List;

public class Render {

    public int x;
    public int y;
    public List<char[]> image;
    public AffineTransform transform;

    public Render() {
    }

    public Render(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        this.image = Util.loadImageAsCharArray(imagePath);
    }

    public void render(TextGraphics textGraphics) {
        for (int i = 0; i < image.size(); i++) {
            char[] row = image.get(i);
            for (int j = 0; j < row.length; j++) {
                textGraphics.setCharacter(x + j, y + i, new TextCharacter(row[j]));
            }
        }
    }
}
