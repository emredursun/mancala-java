package mancala.domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    Player playerOne = new Player(new Kalaha());
    Player playerTwo = new Player(new Kalaha());

    @Test
    public void initializeANewPlayerWhoHasSixBowls(){
        List<Bowl> bowls = playerOne.getBowls();
        assertEquals(6, bowls.size());
    }

    @Test
    public void testIfAPlayerHas24StonesWhenTheGameStarts(){
        assertEquals(24, playerOne.getTotalNumberOfStonesInAllBowls());
        // Player has 0 stones per Kalaha at the beginning of the game
        assertEquals(0, playerOne.getScore());
    }

    @Test
    public void testIfTotalNumberOfStonesDecreaseWhenAPlayerMakesAMove(){
        Bowl bowl = playerOne.getBowls().get(0);
        bowl.emptyBowl();
        assertEquals(20, playerOne.getTotalNumberOfStonesInAllBowls());
    }

    @Test
    public void testIfPlayersHaveNoStonesInTheirKalahaWhenGameJustStarts(){
        // PlayerOne Score
        assertEquals(0, playerOne.getScore());

        //PlayerTwo Score
        assertEquals(0, playerTwo.getScore());
    }

    @Test
    public void testIfAPlayerHasOneStoneInTheirKalahaAndTheirScoreIsOne(){
        Kalaha kalaha = new Kalaha();
        Player player = new Player(kalaha);
        kalaha.addOneStone();

        assertEquals(player.getScore(), kalaha.getStonesPerKalaha());
    }

    @Test
    public void testIfHasNoStonesLeftMethodsReturnFalseWhenAllBowlsOfPlayersAreEmpty(){
            Player playerOne = new Player(new Kalaha());
            Player playerTwo = new Player(new Kalaha());

            // Empty all bowls for both players
            for (Bowl bowl : playerOne.getBowls()) {
                bowl.emptyBowl();
            }
            for (Bowl bowl : playerTwo.getBowls()) {
                bowl.emptyBowl();
            }

            // Assert that both players have no stones left
            assertFalse(playerOne.hasStonesLeft());
            assertFalse(playerTwo.hasStonesLeft());
    }

    @Test
    public void testIfHasNoStonesLeftMethodsReturnTrueWhenAPlayerHasStillStones(){
        Player playerOne = new Player(new Kalaha());
        Bowl bowl = playerOne.getBowls().get(0);
        bowl.emptyBowl();

        assertTrue(playerOne.hasStonesLeft());
    }

    @Test
    public void testIfTheBowlIsEmptyAfterAPlayerMakesMove(){
        Player player = new Player(new Kalaha());
        player.makeMove(2, new ArrayList<>());

        assertEquals(0, player.getBowls().get(1).getStonesPerBowl());
    }

    @Test
    public void testStoneDistributionAfterPlayerMove() {
        Player playerOne = new Player(new Kalaha());
        Player playerTwo = new Player(new Kalaha());
        Mancala mancala = new Mancala(playerOne, playerTwo);

        // Set up the board with a specific distribution of stones
        List<Bowl> playerOneBowls = playerOne.getBowls();
        playerOneBowls.get(0);
        playerOneBowls.get(1).emptyBowl();
        playerOneBowls.get(2).emptyBowl();
        playerOneBowls.get(3).emptyBowl();
        playerOneBowls.get(4).emptyBowl();
        playerOneBowls.get(5).emptyBowl();
        playerOne.getKalaha().addStones(1);

        List<Bowl> playerTwoBowls = playerTwo.getBowls();
        playerTwo.getBowls().get(0).addStones(3);
        playerTwo.getBowls().get(1).addStones(3);
        playerTwo.getBowls().get(2).addStones(3);
        playerTwo.getBowls().get(3).addStones(3);
        playerTwo.getBowls().get(4).addStones(3);
        playerTwo.getBowls().get(5).addStones(3);
        playerTwo.getKalaha().addStones(3);

        assertEquals(4, playerOneBowls.get(0).getStonesPerBowl());
        assertEquals(0, playerOneBowls.get(1).getStonesPerBowl());
        assertEquals(0, playerOneBowls.get(2).getStonesPerBowl());
        assertEquals(0, playerOneBowls.get(3).getStonesPerBowl());
        assertEquals(0, playerOneBowls.get(4).getStonesPerBowl());
        assertEquals(0, playerOneBowls.get(5).getStonesPerBowl());
        assertEquals(1, playerOne.getKalaha().getStonesPerKalaha());

        assertEquals(7, playerTwoBowls.get(0).getStonesPerBowl());
        assertEquals(7, playerTwoBowls.get(1).getStonesPerBowl());
        assertEquals(7, playerTwoBowls.get(5).getStonesPerBowl());
        assertEquals(3, playerTwo.getKalaha().getStonesPerKalaha());

        // PlayerOne perform a move
        mancala.playerMove(1);

        // Check that the stones were distributed correctly
        List<Bowl> updatedPlayerOneBowls = playerOne.getBowls();
        List<Bowl> updatedPlayerTwoBowls = playerTwo.getBowls();
        assertEquals(0, updatedPlayerOneBowls.get(0).getStonesPerBowl());
        assertEquals(1, updatedPlayerOneBowls.get(1).getStonesPerBowl());
        assertEquals(1, updatedPlayerOneBowls.get(2).getStonesPerBowl());
        assertEquals(1, updatedPlayerOneBowls.get(3).getStonesPerBowl());
        assertEquals(0, updatedPlayerOneBowls.get(4).getStonesPerBowl());
        assertEquals(0, updatedPlayerOneBowls.get(5).getStonesPerBowl());
        assertEquals(9, playerOne.getKalaha().getStonesPerKalaha());

        assertEquals(7, updatedPlayerTwoBowls.get(0).getStonesPerBowl());
        assertEquals(0, updatedPlayerTwoBowls.get(1).getStonesPerBowl());
        assertEquals(7, updatedPlayerTwoBowls.get(2).getStonesPerBowl());
        assertEquals(7, updatedPlayerTwoBowls.get(3).getStonesPerBowl());
        assertEquals(7, updatedPlayerTwoBowls.get(4).getStonesPerBowl());
        assertEquals(7, updatedPlayerTwoBowls.get(5).getStonesPerBowl());
        assertEquals(3, playerTwo.getKalaha().getStonesPerKalaha());
    }

}

