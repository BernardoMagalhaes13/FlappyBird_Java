package com.flappybirdg07.Game;

public abstract class Element implements Drawn {

    protected DrawElement drawObject;
    protected Position position;

    public Element(Position p) {
        this.position = p;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw() {
        setDrawParameters();
        if (drawObject == null) return;
        drawObject.draw();
    }
}