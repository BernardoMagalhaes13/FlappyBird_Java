package FlappyBird.Tests;

import com.flappybirdg07.Game.Seed;
import com.flappybirdg07.Game.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SeedTest {

    @Test
    public void ConstructorSeed() {
        Position position = new Position(0, 0);

        Seed seed = new Seed(position);

        assertEquals(position, seed.getPosition());

        Position position2 = new Position(10, 10);
        seed.setPosition(position2);

        assertEquals(position2, seed.getPosition());

    }

    @Test
    public void seedActive() {
        Position position = new Position(0, 0);

        Seed seed = new Seed(position);

        assertEquals(true, seed.isActive());
        seed.setActive(false);

        assertEquals(false, seed.isActive());

    }
}
