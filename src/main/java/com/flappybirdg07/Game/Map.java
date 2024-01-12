package com.flappybirdg07.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    private static final int PIPE_WIDTH = 4;
    private static final int GAME_SPEED = 1;
    private static final int LIMIT_HEIGHT = 1;
    private static final int FIXED_GAP_HEIGHT = 9;
    private static final int RANDOM_GAP_HEIGHT = 4;
    private static final int FIXED_UPPIPE_HEIGHT = 2;
    private static final int RANDOM_UPPIPE_HEIGHT = 12;
    private static final int PIPE_MIN_DISTANCE = 20;
    private static final int SEED_MIN_HEIGHT = 5;

    Position position = new Position(11, 10);
    Bird bird = new Bird(position);
    Score score = new Score(new Position(0, 0));
    Background background;

    private int width;
    private int height;
    private List<Limit> limits;
    private List<Pipe> pipes = new ArrayList<>();
    private List<Seed> seeds = new ArrayList<>();
    private boolean[][] occupiedSpace;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        this.limits = createLimits();
        occupiedSpace = new boolean[height][width];
        background = new Background(new Position(0, 0), width, height);

        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++)
                occupiedSpace[i][j] = false;
    }

    public static int getGameSpeed() {
        return GAME_SPEED;
    }

    public static int getPipeMinDistance() {
        return PIPE_MIN_DISTANCE;
    }

    public static int getPipeWidth() { return PIPE_WIDTH; }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int arrayPipeSize() {
        return pipes.size();
    }

    public int arrayLimitSize() {
        return limits.size();
    }

    public int arraySeedSize() {
        return seeds.size();
    }

    public Position getPosition() {
        return position;
    }

    public Bird getBird() {
        return bird;
    }

    public Score getScore() {
        return score;
    }


    public void moveMap() {
        updatePipes();
        updateLimits();
        updateSeeds();
    }

    public void updatePipes() {
        movePipes();
        deactivateInvalidPipes();
    }

    public void updateSeeds() {
        moveSeeds();
        deactivateInvalidSeeds();
    }

    public void updateLimits() {
        moveLimits();
        deactivateInvalidLimits();
        updateExistingLimits();
    }

    public void movePipes() {
        for (int i = 0; i < pipes.size(); i++) {
            int newX = pipes.get(i).getPosition().getX() - GAME_SPEED;

            Position position = new Position(newX, pipes.get(i).getPosition().getY());
            pipes.get(i).setPosition(position);

            if (pipes.get(i).getPosition().getX() == PIPE_WIDTH) {
                score.increaseScore();
            }
        }
    }


    public void deactivateInvalidPipes() {
        for (int i = 0; i < pipes.size(); i++)
            if (pipes.get(i).getPosition().getX() < -PIPE_WIDTH) {
                pipes.get(i).setActive(false);
            }
    }

    public void moveSeeds() {
        for (int i = 0; i < seeds.size(); i++) {
            int newX = seeds.get(i).getPosition().getX() - GAME_SPEED;

            Position position = new Position(newX, seeds.get(i).getPosition().getY());
            seeds.get(i).setPosition(position);
        }
    }

    public void deactivateInvalidSeeds() {
        for (int i = 0; i < seeds.size(); i++)
            if (seeds.get(i).getPosition().getX() < 0) {
                seeds.get(i).setActive(false);
            }
    }


    public void moveLimits() {
        for (int i = 0; i < limits.size(); i++) {
            int newX = limits.get(i).getPosition().getX() - GAME_SPEED;

            Position position = new Position(newX, limits.get(i).getPosition().getY());
            limits.get(i).setPosition(position);
        }
    }


    public void deactivateInvalidLimits() {
        for (int i = 0; i < limits.size(); i++)
            if (limits.get(i).getPosition().getX() < 0) {
                limits.get(i).setActive(false);
            }
    }

    public boolean entersOccupiedSpace() {
        resetMapOccupation();
        setPipesOccupation();
        setLimitsOccupation();

        return setBirdOccupation();
    }

    private boolean setBirdOccupation() {
        int bX = bird.getPosition().getX();
        int bY = bird.getPosition().getY() + 1;

        for (int i = bY; i < bY + 2; i++)           //     p
            for (int j = bX - 3; j < bX + 2; j++)   // # # # #  the bird is a rectangle
                if (!occupiedSpace[i][j]) {         // # # # #  and 'p' as its position
                    occupiedSpace[i][j] = true;
                } else {
                    return true;
                }
        return false;
    }

    private void setPipesOccupation() {
        for (Pipe pipe : pipes)
            for (int i = 0; i < pipe.getHeight(); i++)
                for (int j = 0; j < pipe.getWidth(); j++)
                    if (pipe.getPosition().getX() >= 0)
                        occupiedSpace[pipe.getPosition().getY() + i * pipe.getOption()][pipe.getPosition().getX() + j] = true;
    }

    private void setLimitsOccupation() {
        for (int i = 0; i < height; i++)
            for (int j = 0; j < width; j++) {
                occupiedSpace[0][i] = true;
                occupiedSpace[height - 1][i] = true;
            }
    }

    private void resetMapOccupation() {
        for (int i = 1; i < height - 1; i++)
            for (int j = 0; j < width; j++)
                occupiedSpace[i][j] = false;
    }

    public void gravityBird() {
        bird.dive();
    }

    public void moveBird() {
        bird.fly();
    }

    public void computePipes() {
        Random random = new Random();
        int upPipe, downPipe, gap;

        gap = random.nextInt(RANDOM_GAP_HEIGHT) + FIXED_GAP_HEIGHT;
        upPipe = random.nextInt(RANDOM_UPPIPE_HEIGHT) + FIXED_UPPIPE_HEIGHT;
        downPipe = height - upPipe - gap;
        Position p = new Position(width - PIPE_WIDTH, upPipe);
        Position p1 = new Position(width - PIPE_WIDTH, height - downPipe);

        if (updateExistingPipes(upPipe, downPipe, p, p1)) return;
        createNewPipes(upPipe, downPipe, p, p1);
    }

    private boolean updateExistingPipes(int upPipe, int downPipe, Position p, Position p1) {
        for (int i = 0; i < pipes.size(); i++) {
            if (!pipes.get(i).isActive()) {
                pipes.get(i).setPipe(p, upPipe);
                pipes.get(i).setActive(true);
                pipes.get(i + 1).setPipe(p1, downPipe - 1);
                pipes.get(i + 1).setActive(true);
                return true;
            }
        }
        return false;
    }

    private void createNewPipes(int upPipe, int downPipe, Position p, Position p1) {
        pipes.add(new Pipe(p, upPipe, PIPE_WIDTH, -1)); // up
        pipes.add(new Pipe(p1, downPipe - 1, PIPE_WIDTH, 1)); // down
    }

    public void caughtSeed() {
        int bX = bird.getPosition().getX();
        int bY = bird.getPosition().getY() + 1;         //     p
        for (int i = bY; i < bY + 2; i++) {             // # # # #  the bird is a rectangle
            for (int j = bX - 3; j < bX + 2; j++) {     // # # # #  the bird is a rectangle
                Position birdP = new Position(j, i);
                for (int k = 0; k < seeds.size(); k++) {
                    if (seeds.get(k).isActive()) {
                        if (seeds.get(k).getPosition().equals(birdP)) {
                            seeds.get(k).setActive(false);
                            score.increaseScore();
                            return;
                        }
                        break;
                    }
                }
            }
        }
    }

    public void computeSeeds() {
        Random random = new Random();
        int posX = random.nextInt(PIPE_MIN_DISTANCE) + width;
        int posY = random.nextInt(height - 2 * SEED_MIN_HEIGHT) + SEED_MIN_HEIGHT;
        Position p = new Position(posX, posY);

        if (updateExistingSeeds(p)) return;

        seeds.add(new Seed(p));
    }

    private boolean updateExistingSeeds(Position p) {
        for (int i = 0; i < seeds.size(); i++) {
            if (!seeds.get(i).isActive()) {
                seeds.get(i).setActive(true);
                seeds.get(i).setPosition(p);
                return true;
            }
        }

        return false;
    }

    public void updateExistingLimits() {
        for (int i = 0; i < limits.size(); i++) {
            if (!limits.get(i).isActive()) {
                limits.get(i).setPosition(new Position(width - 1, 0));
                limits.get(i).setActive(true); // top Limit
                limits.get(i + 1).setPosition(new Position(width - 1, height - 1));
                limits.get(i + 1).setActive(true); // bot Limit
                return;
            }
        }
    }

    public List<Limit> createLimits() {
        List<Limit> limits = new ArrayList<>();

        for (int c = 0; c < width; c += 2) {
            Position topPosition = new Position(c, 0);
            Position botPosition = new Position(c, height - LIMIT_HEIGHT);
            limits.add(new Limit(topPosition));
            limits.add(new Limit(botPosition));
        }

        return limits;
    }


    public void addPipe(Pipe pipe) {
        pipes.add(pipe);
    }

    public void addSeed(Seed seed) {
        seeds.add(seed);
    }

    public void addLimit(Limit limit) {
        limits.add(limit);
    }

    public List<Pipe> getPipes() {
        return pipes;
    }

    public List<Seed> getSeeds() {
        return seeds;
    }

    public List<Limit> getLimits() {
        return limits;
    }

    public void draw() {
        background.draw();

        for (int i = 0; i < limits.size(); i++)
            if (limits.get(i).isActive()) {
                limits.get(i).draw();
            }

        for (int i = 0; i < pipes.size(); i++)
            if (pipes.get(i).isActive()) {
                pipes.get(i).draw();
            }

        for (int i = 0; i < seeds.size(); i++)
            if (seeds.get(i).isActive()) {
                seeds.get(i).draw();
            }

        //score.draw();
        bird.draw();
    }
    public void reset() {
        pipes.clear();
        seeds.clear();
        score.reset();
        bird.reset();
        background.draw();
    }

}

