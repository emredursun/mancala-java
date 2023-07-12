package mancala.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MancalaTest {
    Player playerOne = new Player (new Kalaha());
    Player playerTwo = new Player (new Kalaha());
    Player activePlayer = playerOne;
    Mancala mancalaGame = new Mancala (playerOne, playerTwo);


    @Test
    public void testIfPlayersAreInitialized (){
        assertEquals(playerOne, mancalaGame.getPlayerOne());
        assertEquals(playerTwo, mancalaGame.getPlayerTwo());
    }

    @Test
    public void testIfGameNotEnded(){
            assertFalse(mancalaGame.isGameEnded());
    }

    @Test
    public void testIfGameEndsWhenPlayerHasNoStonesLeft(){
        mancalaGame.getPlayerOne().getBowls().forEach(Bowl::emptyBowl);
        assertTrue(mancalaGame.isGameEnded());
    }

    @Test
    public void testIfPlayerOneIsActivePlayerWhenGameStarts(){
        assertEquals(activePlayer, mancalaGame.getActivePlayer());
    }

    @Test
    public void testIfPlayerIsSwitchedWhenTurnEnds(){
        mancalaGame.switchPlayer();
        assertEquals(playerTwo, mancalaGame.getActivePlayer());
    }

    @Test
    public void testIfNumberOfStonesInBowlsChangeAfterMove(){
        // Before making a move
        for(int i = 0; i < 6; i++){
            assertEquals(4, activePlayer.getBowls().get(i).getStonesPerBowl());
        }

        // After making a move
        mancalaGame.playerMove(2);
        assertEquals(4, activePlayer.getBowls().get(0).getStonesPerBowl());
        assertEquals(0, activePlayer.getBowls().get(1).getStonesPerBowl());
        assertEquals(5, activePlayer.getBowls().get(2).getStonesPerBowl());
        assertEquals(5, activePlayer.getBowls().get(3).getStonesPerBowl());
        assertEquals(5, activePlayer.getBowls().get(4).getStonesPerBowl());
        assertEquals(5, activePlayer.getBowls().get(5).getStonesPerBowl());

        assertEquals(0, playerOne.getScore());

        // The active player switch to another player
        assertEquals(playerTwo, mancalaGame.getActivePlayer());
    }

    @Test
    public void testIfActivePlayerIsNotSwitchedWhenMoveEndsInKalaha(){
        // Before making a move
        for(int i = 0; i < 6; i++){
            assertEquals(4, activePlayer.getBowls().get(i).getStonesPerBowl());
        }

        // After making move with bowl number 3
        mancalaGame.playerMove(3);
        assertEquals(4, activePlayer.getBowls().get(0).getStonesPerBowl());
        assertEquals(4, activePlayer.getBowls().get(1).getStonesPerBowl());
        assertEquals(0, activePlayer.getBowls().get(2).getStonesPerBowl());
        assertEquals(5, activePlayer.getBowls().get(3).getStonesPerBowl());
        assertEquals(5, activePlayer.getBowls().get(4).getStonesPerBowl());
        assertEquals(5, activePlayer.getBowls().get(5).getStonesPerBowl());

        assertEquals(1, playerOne.getScore());

        assertEquals(playerOne, mancalaGame.getActivePlayer());
    }

    @Test
    public void testIfActivePlayerIsSwitchedWhenMoveEndsInOpponentBowl(){

        // PlayerOne makes a move with his bowl six
        mancalaGame.playerMove(6);

        // PlayerOne Board
        assertEquals(4, playerOne.getBowls().get(0).getStonesPerBowl());
        assertEquals(4, playerOne.getBowls().get(1).getStonesPerBowl());
        assertEquals(4, playerOne.getBowls().get(2).getStonesPerBowl());
        assertEquals(4, playerOne.getBowls().get(3).getStonesPerBowl());
        assertEquals(4, playerOne.getBowls().get(4).getStonesPerBowl());
        assertEquals(0, playerOne.getBowls().get(5).getStonesPerBowl());

        assertEquals(1, playerOne.getScore());

        // PlayerTwo Board
        assertEquals(5, playerTwo.getBowls().get(0).getStonesPerBowl());
        assertEquals(5, playerTwo.getBowls().get(1).getStonesPerBowl());
        assertEquals(5, playerTwo.getBowls().get(2).getStonesPerBowl());
        assertEquals(4, playerTwo.getBowls().get(3).getStonesPerBowl());
        assertEquals(4, playerTwo.getBowls().get(4).getStonesPerBowl());
        assertEquals(4, playerTwo.getBowls().get(5).getStonesPerBowl());

        assertEquals(0, playerTwo.getScore());

        // PlayerOne switches to PlayerTwo and PlayerTwo makes a move with his bowl 2 to end in his kalaha
        assertEquals(playerTwo, mancalaGame.getActivePlayer());

        mancalaGame.playerMove(2);
        // PlayerTwo Board
        assertEquals(5, playerTwo.getBowls().get(0).getStonesPerBowl());
        assertEquals(0, playerTwo.getBowls().get(1).getStonesPerBowl());
        assertEquals(6, playerTwo.getBowls().get(2).getStonesPerBowl());
        assertEquals(5, playerTwo.getBowls().get(3).getStonesPerBowl());
        assertEquals(5, playerTwo.getBowls().get(4).getStonesPerBowl());
        assertEquals(5, playerTwo.getBowls().get(5).getStonesPerBowl());

        assertEquals(1, playerTwo.getScore());

        // PlayerTwo is still the active player
        activePlayer = playerTwo;
        assertEquals(activePlayer, mancalaGame.getActivePlayer());
    }

    @Test
    public void testIfWinnerIsDeclaredWhenAllBowlsOfAPlayerAreEmpty(){
        // Updating the number of stone as Zero in the PlayerOne's bowls
        activePlayer = playerOne;
        for(int i = 0; i < 6; i++){
            activePlayer.getBowls().get(i).emptyBowl();
        }

        activePlayer.getBowls().get(5).addOneStone();

        // PlayerOne Board
        assertEquals(0, playerOne.getBowls().get(0).getStonesPerBowl());
        assertEquals(0, playerOne.getBowls().get(1).getStonesPerBowl());
        assertEquals(0, playerOne.getBowls().get(2).getStonesPerBowl());
        assertEquals(0, playerOne.getBowls().get(3).getStonesPerBowl());
        assertEquals(0, playerOne.getBowls().get(4).getStonesPerBowl());
        assertEquals(1, playerOne.getBowls().get(5).getStonesPerBowl());

        // Kick Off attempt :)
        mancalaGame.playerMove(6);

        // Check if playerOne has no stones in his bowls
        assertFalse(playerOne.hasStonesLeft());


        int playerOneScore = playerOne.getScore();
        int playerTwoScore = playerTwo.getScore();
        String formattedString = String.format("playerOne Score: %s \nplayerTwo Score: %s", playerOneScore, playerTwoScore);
        System.out.println(formattedString);

        // Declare who is winner
        assertEquals(playerOne, mancalaGame.getWinner());
    }
}
