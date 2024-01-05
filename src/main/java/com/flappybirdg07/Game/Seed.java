package com.flappybirdg07.Game;

public class Coin extends Element {
    private boolean active;

    public Coin(Position position) {
        super(position);
        active = true;
    }

    public Position getPosition() {
        return super.getPosition();
    }

    public void setPosition(Position position) {
        super.setPosition(position);
    }


    @Override
    public void setDrawParameters() {
        drawObject = FlappyBird.getInstance().getDrawingFactory().getCoinDrawElement();
        drawObject.setDrawParameters(position);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}

