
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.flappybirdg07.Bird;
import com.flappybirdg07.Game;
import com.flappybirdg07.Pipes;
import com.flappybirdg07.Position;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    private Game game;

    @Before
    public void setUp() {
        // Modifique o construtor do Game para aceitar um objeto TerminalScreen mock
        TerminalScreen mockTerminalScreen = null;
        game = new Game(mockTerminalScreen);
    }

    @Test
    public void testCheckCollisionsWithCollision() {
        // Crie um cenário onde há uma colisão
        Bird bird = game.getBird();
        bird.setPosition(new Position(40, 10));

        game.adder(new Pipes(new Position(40, 0)));

        game.checkCollisions();

        // Verifique se o jogo foi encerrado após a colisão
        assertFalse(game.isJogoEmExecucao());
    }

    @Test
    public void testCheckCollisionsNoCollision() {
        // Crie um cenário onde não há colisão
        game.getBird().setPosition(new Position(40, 10));
        game.adder(new Pipes(new Position(60, 0)));

        game.checkCollisions();

        // Verifique se o jogo não foi encerrado sem colisão
        assertTrue(game.isJogoEmExecucao());
    }

    @Test
    public void testCheckCollisionsWithBorderCollision() {
        // Crie um cenário onde há uma colisão com a borda
        game.getBird().setPosition(new Position(10, 0));

        game.checkCollisions();

        // Verifique se o jogo foi encerrado após a colisão com a borda
        assertFalse(game.isJogoEmExecucao());
    }

    @Test
    public void testCheckCollisionsNoBorderCollision() {
        // Crie um cenário onde não há colisão com a borda
        game.getBird().setPosition(new Position(10, 10));

        game.checkCollisions();

        // Verifique se o jogo não foi encerrado sem colisão com a borda
        assertTrue(game.isJogoEmExecucao());
    }

    // Adicione mais testes conforme necessário
}
