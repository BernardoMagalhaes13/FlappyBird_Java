package FlappyBird.Tests;

import com.flappybirdg07.Game.*;

import com.flappybirdg07.Draw.DrawFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class GameOverTest {

    private GameOver gameOver;

    @Before
    public void setUp() {
        FlappyBird flappyBird = mock(FlappyBird.class);
        DrawFactory drawFactory = mock(DrawFactory.class);
        when(flappyBird.getDrawingFactory()).thenReturn(drawFactory);

        gameOver = new GameOver(new Position(0, 0), 10, 20);
    }

    @Test
    public void testGetWidth() {
        assertEquals(10, gameOver.getWidth());
    }

    @Test
    public void testSetWidth() {
        gameOver.setWidth(15);
        assertEquals(15, gameOver.getWidth());
    }

    @Test
    public void testGetHeight() {
        assertEquals(20, gameOver.getHeight());
    }

    @Test
    public void testSetHeight() {
        gameOver.setHeight(25);
        assertEquals(25, gameOver.getHeight());
    }
}
