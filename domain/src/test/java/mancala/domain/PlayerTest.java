package mancala.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class PlayerTest {
    private Player player;
    private Kalaha kalaha;

    @BeforeEach
    public void setUp() {
        kalaha = new Kalaha();
        player = new Player(kalaha);
    }

    @Test
    public void testGetScore() {
        Assertions.assertEquals(0, player.getScore());
    }

    @Test
    public void testGetCups() {
        Assertions.assertEquals(6, player.getCups().size());
    }

    @Test
    public void testGetTotalStones() {
        Assertions.assertEquals(24, player.getTotalStones());
    }

    @Test
    public void testHasStonesLeft() {
        Assertions.assertTrue(player.hasStonesLeft());
    }

}

