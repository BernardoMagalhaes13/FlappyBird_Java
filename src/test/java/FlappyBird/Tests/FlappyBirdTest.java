package FlappyBird.Tests;

import com.flappybirdg07.Game.*;

import com.flappybirdg07.Draw.DrawFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FlappyBirdTest {

    private FlappyBird flappyBird;

    @Before
    public void setUp() {
        flappyBird = FlappyBird.getInstance();
    }

    @Test
    public void testGetBirdFallingSpeed() {
        assertEquals(100F, FlappyBird.getBirdFallingSpeed(), 0.001);
    }

    @Test
    public void testGetGameStateStart() {
        assertEquals(FlappyBird.gameState.Start, flappyBird.getGameState());
    }

    @Test
    public void testGetDrawingFactory() {
        DrawFactory drawFactory = flappyBird.getDrawingFactory();
        assertEquals(DrawFactory.class, drawFactory.getClass());
    }

    @Test
    public void testRun() {
        try {
            flappyBird.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testUpdateBird() {
        long moveBird = System.currentTimeMillis();
        long updatedMoveBird = flappyBird.updateBird(moveBird);

        assertEquals(moveBird, updatedMoveBird);
    }

    @Test
    public void testUpdateMap() {
        long startDraw = System.currentTimeMillis();
        long updatedStartDraw = flappyBird.updateMap(startDraw);

        assertEquals(startDraw, updatedStartDraw);
    }
}

