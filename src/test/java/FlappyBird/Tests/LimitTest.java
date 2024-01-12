package FlappyBird.Tests;

import static org.junit.jupiter.api.Assertions.*;

import com.flappybirdg07.Game.Limit;
import com.flappybirdg07.Game.Position;
import org.junit.jupiter.api.Test;

public class LimitTest {

    @Test
    public void testGetPosition() {
        Position expectedPosition = new Position(10, 20);
        Limit limit = new Limit(expectedPosition);

        Position actualPosition = limit.getPosition();

        assertEquals(expectedPosition, actualPosition);
    }

    @Test
    public void testIsActive() {
        Limit limit = new Limit(new Position(10, 20));

        boolean isActive = limit.isActive();

        assertTrue(isActive);
    }

    @Test
    public void testSetAndGetActive() {
        Limit limit = new Limit(new Position(10, 20));

        limit.setActive(false);

        assertFalse(limit.isActive());
    }
}
