package com.flappybirdg07.Game;

public class Background extends Element {
    private int width;
    private int height;

    public Background(Position p, int width, int height) {
        super(p);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void setDrawParameters() {
        drawObject = FlappyBird.getInstance().getDrawingFactory().getBackgroundDrawElement();
        drawObject.setDrawParameters(position, width, height);
    }
}
