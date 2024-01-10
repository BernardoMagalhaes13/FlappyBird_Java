package com.flappybirdg07.Game;

public class Pipe extends Element {
    private int height;
    private int width;
    private int option;
    private boolean active;

    public Pipe(Position position, int height, int width, int option) {
        super(position);
        this.height = height;
        this.width = width;
        this.option = option;
        this.active = true;
    }

    public Position getPosition() {
        return super.getPosition();
    }

    public void setPosition(Position position) {
        super.setPosition(position);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getOption() {
        return option;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPipe(Position position, int height) {
        super.setPosition(position);
        this.height = height;
    }


    @Override
    public void setDrawParameters() {
        drawObject = FlappyBird.getInstance().getDrawFactory().getPipeDrawElement();
        drawObject.setDrawParameters(position, height, width, option);
    }
}
