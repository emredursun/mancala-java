package mancala.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


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
    public void testGetBowls() {
        Assertions.assertEquals(6, player.getBowls().size());
    }

    @Test
    public void testGetTotalStones() {
        Assertions.assertEquals(24, player.getTotalStones());
    }

    @Test
    public void testHasStonesLeft() {
        Assertions.assertTrue(player.hasStonesLeft());
    }

    @Test
    public void testMakeMoveAndEndsInOwnBowl(){
        List<Bowl> activePlayerBowls = player.getBowls();
        List<Bowl> passivePlayerBowls = new ArrayList<>();

        player.makeMove(2, passivePlayerBowls);

        Assertions.assertEquals(4, activePlayerBowls.get(0).getStones());
        Assertions.assertEquals(0, activePlayerBowls.get(1).getStones());
        Assertions.assertEquals(5, activePlayerBowls.get(2).getStones());
        Assertions.assertEquals(5, activePlayerBowls.get(3).getStones());
        Assertions.assertEquals(5, activePlayerBowls.get(4).getStones());
        Assertions.assertEquals(5, activePlayerBowls.get(5).getStones());

    }
}

