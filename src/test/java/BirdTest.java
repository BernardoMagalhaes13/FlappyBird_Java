import com.flappybirdg07.Bird;
import com.flappybirdg07.Position;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BirdTest {

    private Bird bird;

    @Before
    public void setUp() {
        bird = new Bird(new Position(10, 10));
    }

    @Test
    public void testMove() {
        bird.move();
        // Verifique se o pássaro se moveu corretamente
        assertEquals(11, bird.getPosition().getY());
    }

    @Test
    public void testJump() {
        bird.jump();
        // Verifique se o pássaro pulou corretamente
        assertEquals(-2, bird.getVelocity());
    }

    @Test
    public void testApplyGravity() {
        bird.applyGravity();
        // Verifique se a gravidade foi aplicada corretamente
        assertEquals(1, bird.getVelocity());
    }

    // Adicione mais testes conforme necessário
}
