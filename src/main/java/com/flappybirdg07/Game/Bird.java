package com.flappybirdg07.Game;

public class Bird extends Element {
    private static final int FALL_PACE = 1;
    private static final int FLY_PACE = 4;

    public Bird(Position position) {
        super(position);
    }

    public static int getFlyPace() {
        return FLY_PACE;
    }

    public static int getFallPace() {
        return FALL_PACE;
    }

    public Position getPosition() {
        return super.getPosition();
    }

    public void setPosition(Position position) {
        super.setPosition(position);
    }

    public void dive() {
        Position newPosition = new Position(super.getPosition().getX(), super.getPosition().getY() + FALL_PACE);
        super.setPosition(newPosition);
    }

    public void fly() {
        Position newPosition = new Position(super.getPosition().getX(), super.getPosition().getY() - FLY_PACE);
        super.setPosition(newPosition);
    }

    public void setDrawParameters() {
        drawObject = FlappyBird.getInstance().getDrawFactory().getBirdDrawElement();
        drawObject.setDrawParameters(position);
    }
}
