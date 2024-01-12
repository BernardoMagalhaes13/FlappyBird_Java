package FlappyBird.Tests;

import com.flappybirdg07.Game.*;

import com.flappybirdg07.Draw.DrawFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BackGroundTest {

    private Background background;
    @Test
    public void ConstructorBackground() {
        Position position = new Position(0, 0);
        int width = 80;
        int heigth = 24;
        Background background = new Background(position, width, heigth);

        assertEquals(position, background.getPosition());
        assertEquals(width, background.getWidth());
        assertEquals(heigth, background.getHeight());
    }

    @Before
    public void setUp() {
        FlappyBird flappyBird = mock(FlappyBird.class);
        DrawFactory drawFactory = mock(DrawFactory.class);
        when(flappyBird.getDrawingFactory()).thenReturn(drawFactory);

        background = new Background(new Position(0, 0), 10, 20);
    }

    @Test
    public void testGetWidth() {
        assertEquals(10, background.getWidth());
    }

    @Test
    public void testGetHeight() {
        assertEquals(20, background.getHeight());
    }


}
