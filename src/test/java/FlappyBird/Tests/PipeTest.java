package FlappyBird.Tests;

import com.flappybirdg07.Game.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class PipeTest {

    @Test
    public void Constructorpipe() {
        Position position = new Position(0, 0);
        Pipe pipe = new Pipe(position, 0, 0, 0);

        assertEquals(position, pipe.getPosition());
        assertEquals(0, pipe.getHeight());
        assertEquals(0, pipe.getWidth());
        assertEquals(0, pipe.getOption());
    }

    @Test
    public void pipeSets() {
        Position position = new Position(0, 0);
        Pipe pipe = new Pipe(position, 0, 0, 0);

        Position position1 = new Position(2, 2);
        pipe.setPosition(position1);
        assertEquals(position1, pipe.getPosition());

        assertTrue(pipe.isActive());
        pipe.setActive(false);
        assertFalse(pipe.isActive());

        Position position2 = new Position(5, 5);
        pipe.setPipe(position2, 5);
        assertEquals(position2, pipe.getPosition());
        assertEquals(5, pipe.getHeight());
    }
    private Pipe pipe;

    @Test
    public void testGetPosition() {
        assertEquals(new Position(0, 0), pipe.getPosition());
    }

    @Test
    public void testSetPosition() {
        pipe.setPosition(new Position(5, 5));
        assertEquals(new Position(5, 5), pipe.getPosition());
    }

    @Test
    public void testGetHeight() {
        assertEquals(10, pipe.getHeight());
    }

    @Test
    public void testGetWidth() {
        assertEquals(20, pipe.getWidth());
    }

    @Test
    public void testGetOption() {
        assertEquals(1, pipe.getOption());
    }

    @Test
    public void testIsActive() {
        assertTrue(pipe.isActive());
    }

    @Test
    public void testSetActive() {
        pipe.setActive(false);
        assertFalse(pipe.isActive());
    }

    @Test
    public void testSetPipe() {
        pipe.setPipe(new Position(1, 1), 15);
        assertEquals(new Position(1, 1), pipe.getPosition());
        assertEquals(15, pipe.getHeight());
    }
}
