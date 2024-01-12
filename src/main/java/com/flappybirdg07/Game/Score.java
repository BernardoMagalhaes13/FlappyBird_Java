package com.flappybirdg07.Game;

public class Score extends Element {
    private static final int SCORE_PER_PIPE = 5;
    private static final int SCORE_PER_SEED = 5;
    private int scoreTracker;
    private static int bestScore = 0;
    public static final int HEIGHT = 2;

    public Score(Position p) {
        super(p);
    }

    public static int getScorePerSeed() {
        return SCORE_PER_SEED;
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


    public void increaseScore() {
        scoreTracker += SCORE_PER_PIPE;
        updateBestScore();
    }
    public void reset() {
        scoreTracker = 0;
    }

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
