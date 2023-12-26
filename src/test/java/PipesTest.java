import static org.junit.Assert.assertEquals;

import com.flappybirdg07.Pipes;
import org.junit.Before;
import org.junit.Test;

public class PipesTest {

    private Pipes pipes;

    @Before
    public void setUp() {
        pipes = new Pipes(new Position(40, 10));
    }

    @Test
    public void testMove() {
        pipes.move();
        // Verifique se os canos se moveram corretamente
        assertEquals(39, pipes.getPosition().getX());
    }

    // Adicione mais testes conforme necessário
}
