package FlappyBird.Tests;

import com.flappybirdg07.Game.*;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class MapTest {

    @Test
    public void ConstructorMap() {
        int width = 80;
        int height = 24;
        Map map = new Map(width, height);

        assertEquals(width, map.getWidth());
        assertEquals(height, map.getHeight());
        assertEquals(0, map.arrayPipeSize());
        assertEquals(0, map.getScore());
        assertEquals(width, map.arrayLimitSize());
        assertEquals(map.getPosition(), map.getBird().getPosition());
        assertEquals(map.getPosition(), map.getScore().getPosition());
    }


    @Test
    public void removeInvalidPipes() {
        Position position = new Position(-5, 0);
        Position position2 = new Position(10, 0);
        Pipe pipe = new Pipe(position, 0, 0, 0);
        Pipe pipe2 = new Pipe(position2, 0, 0, 0);
        Map map = new Map(80, 24);

        map.addPipe(pipe);
        map.deactivateInvalidPipes();

        assertEquals(false, pipe.isActive());

        map.addPipe(pipe2);
        map.deactivateInvalidPipes();

        assertEquals(true, pipe2.isActive());

        pipe2.setPosition(new Position(-6, 0));
        map.updatePipes();
        assertEquals(false, pipe2.isActive());

    }
    @Test
    public void birdMobility() {
        Map map = new Map(80, 24);

        assertEquals(10, map.getBird().getPosition().getY());
        map.gravityBird();
        assertEquals(10 + Bird.getFallPace(), map.getBird().getPosition().getY());
        map.moveBird();
        assertEquals(10 + Bird.getFallPace() - Bird.getFlyPace(), map.getBird().getPosition().getY());
    }

    @Test
    public void ExcludeInvalidLimits() {
        Position position = new Position(-1, 0);
        Position position2 = new Position(10, 0);
        Limit limit = new Limit(position);
        Limit limit2 = new Limit(position2);
        Map map = new Map(80, 24);

        map.addLimit(limit);
        map.deactivateInvalidLimits();

        assertEquals(false, limit.isActive());

        map.addLimit(limit2);
        map.deactivateInvalidLimits();

        assertEquals(true, limit2.isActive());

        limit2.setPosition(new Position(-4, 0));
        map.updateLimits();

        assertEquals(false, limit2.isActive());
    }
    @Test
    public void moveLimits() {
        Position position = new Position(-1, 0);
        Position position2 = new Position(10, 0);
        Limit limit = new Limit(position);
        Limit limit2 = new Limit(position2);
        Map map = new Map(80, 24);
        Position p = new Position(limit.getPosition().getX() - Map.getGameSpeed(), 0);


        map.addLimit(limit);
        map.moveLimits();

        assertEquals(p.getX(), limit.getPosition().getX());

        Position p2 = new Position(limit2.getPosition().getX() - Map.getGameSpeed(), 0);
        map.addLimit(limit2);
        map.moveMap();

        assertEquals(p2.getX(), limit2.getPosition().getX());
    }

    @Test
    public void PipeMove() {
        Position position = new Position(20, 0);
        Position position2 = new Position(30, 0);
        Pipe pipe = new Pipe(position, 0, 0, 0);
        Pipe pipe2 = new Pipe(position2, 0, 0, 0);
        Position p = new Position(pipe.getPosition().getX() - Map.getGameSpeed(), 0);
        Map map = new Map(80, 24);
        map.addPipe(pipe);
        map.movePipes();

        assertEquals(p.getX(), pipe.getPosition().getX());

        Position p2 = new Position(pipe2.getPosition().getX() - Map.getGameSpeed(), 0);
        map.addPipe(pipe2);
        map.moveMap();

        assertEquals(p2.getX(), pipe2.getPosition().getX());
    }


    @Test
    public void SeedsMove() {
        Position position = new Position(-1, 0);
        Position position2 = new Position(10, 0);
        Seed seed = new Seed(position);
        Seed seed2 = new Seed(position2);
        Map map = new Map(80, 24);
        Position p = new Position(seed.getPosition().getX() - Map.getGameSpeed(), 0);


        map.addSeed(seed);
        map.moveSeeds();

        assertEquals(p.getX(), seed.getPosition().getX());

        Position p2 = new Position(seed2.getPosition().getX() - Map.getGameSpeed(), 0);
        map.addSeed(seed2);
        map.moveMap();

        assertEquals(p2.getX(), seed2.getPosition().getX());
    }

    @Test
    public void removeInvalidSeeds() {
        Position position = new Position(-1, 0);
        Position position2 = new Position(10, 0);
        Seed seed = new Seed(position);
        Seed seed2 = new Seed(position2);
        Map map = new Map(80, 24);

        map.addSeed(seed);
        map.updateSeeds();

        assertEquals(false, seed.isActive());

        map.addSeed(seed2);
        map.deactivateInvalidSeeds();

        assertEquals(true, seed2.isActive());
        seed2.setPosition(new Position(-4, 0));
        map.updateSeeds();

        assertEquals(false, seed2.isActive());
    }

    @Test
    public void PipesCompute() {
        Map map = new Map(80, 24);
        map.computePipes();

        assertTrue(map.arrayPipeSize() == 2);
        assertTrue(map.getPipes().get(0).getPosition().getX() < map.getWidth());

        assertTrue(map.getPipes().get(1).getPosition().getX() < map.getWidth());
    }

    @Test
    public void SeedsCompute() {
        Map map = new Map(80, 24);
        map.computeSeeds();

        assertTrue(map.arraySeedSize() == 1);
        assertTrue(map.getSeeds().get(0).getPosition().getX() < map.getWidth() + Map.getPipeMinDistance());
    }

    @Test
    public void SeedsCatching() {
        Map map = new Map(80, 24);
        Position p = new Position(10, 10);
        Seed seed = new Seed(p);

        map.addSeed(seed);
        map.getBird().setPosition(p);
        assertEquals(seed.getPosition(), map.getBird().getPosition());
        int score = map.getScore().getScoreTracker();

        map.caughtSeed();

        assertEquals(true, seed.isActive()); //     p

        map.getBird().setPosition(new Position(10, 9));

        map.caughtSeed();
        assertEquals(false, seed.isActive());

        assertEquals(score + Score.getScorePerSeed(), map.getScore().getScoreTracker());
    }

    @Test
    public void InteractionMap() {
        Map mockMap;
        mockMap = Mockito.mock(Map.class);

        verifyZeroInteractions(mockMap);

        mockMap.computePipes();

        for (int i = 0; i < 80; i++)
            mockMap.updatePipes();

        mockMap.movePipes();
        mockMap.deactivateInvalidPipes();

        Mockito.verify(mockMap, times(1)).deactivateInvalidPipes();
        Mockito.verify(mockMap, times(80)).updatePipes();
        Mockito.verify(mockMap, times(1)).movePipes();

    }
}