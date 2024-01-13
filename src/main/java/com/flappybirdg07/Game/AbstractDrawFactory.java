package com.flappybirdg07.Game;

import java.io.IOException;

public interface AbstractDrawFactory {

    DrawElement getBirdDrawElement();

    DrawElement getPipeDrawElement();

    DrawElement getLimitDrawElement();

    DrawElement getGameOverDrawElement();

    DrawElement getScoreDrawElement();

    DrawElement getBackgroundDrawElement();

    DrawElement getSeedDrawElement();

    void beginDraw();

    void endDraw();

    void draw();

    void drawGameOver() throws IOException;

    void endGame();

    boolean isKeyPressed() throws IOException;

    void setKeyPressed(boolean keyPressed);
}
