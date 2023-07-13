package mancala.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MancalaGameTest {
    private MancalaGame mancalaGame;
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    public void setUp() {
        playerOne = new Player(new Kalaha());
        playerTwo = new Player(new Kalaha());
        mancalaGame = new MancalaGame(playerOne, playerTwo);
    }

    @Test
    public void testGetPlayerOne() {
        Assertions.assertEquals(playerOne, mancalaGame.getPlayerOne());
    }

    @Test
    public void testGetPlayerTwo() {
        Assertions.assertEquals(playerTwo, mancalaGame.getPlayerTwo());
    }

    @Test
    public void testGetActivePlayer() {
        Assertions.assertEquals(playerOne, mancalaGame.getActivePlayer());
    }

    @Test
    public void testIsGameEnded() {
        Assertions.assertFalse(mancalaGame.isGameEnded());
    }

}
