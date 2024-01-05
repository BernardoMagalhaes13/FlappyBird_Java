package com.flappybirdg07.Game;

public class Score extends Element {
    private static final int SCORE_PER_PIPE = 5;
    private static final int SCORE_PER_COIN = 5;
    private int scoreTracker;
    private static int bestScore = 0;  // Add a static variable for best score

    public Score(Position p) {
        super(p);
    }

    public static int getScorePerCoin() {
        return SCORE_PER_COIN;
    }

    public static int getScorePerPipe() {
        return SCORE_PER_PIPE;
    }

    public int getScoreTracker() {
        return scoreTracker;
    }

    public static int getBestScore() {
        return bestScore;
    }

    /**
     * Increases the current score by a defined SCORE_PER_PIPE value
     */
    public void increaseScore() {
        scoreTracker += SCORE_PER_PIPE;
        updateBestScore();
    }

    // Add this method to update the best score
    private void updateBestScore() {
        if (scoreTracker > bestScore) {
            bestScore = scoreTracker;
        }
    }

    @Override
    public void setDrawParameters() {
        drawObject = FlappyBird.getInstance().getDrawingFactory().getScoreDrawElement();
        drawObject.setDrawParameters(position, scoreTracker);
    }
}
