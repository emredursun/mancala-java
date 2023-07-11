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
    public void CheckIfTwoPlayersAreInitialized (){
        assertEquals(playerOne, mancalaGame.getPlayerOne());
        assertEquals(playerTwo, mancalaGame.getPlayerTwo());
    }

    @Test
    public void CheckIfGameIsNotEnded(){
            assertFalse(mancalaGame.isGameEnded());
    }

    @Test
    public void WhenAPlayerHasNoStonesLeftAGameEnds(){
        mancalaGame.getPlayerOne().getBowls().forEach(Bowl::emptyBowl);
        assertTrue(mancalaGame.isGameEnded());
    }

    @Test
    public void CheckIfPlayerOneIsActivePlayerWhenGameStarts(){
        assertEquals(activePlayer, mancalaGame.getActivePlayer());
    }

    @Test
    public void WhenATurnEndsThePlayerIsSwitched(){
        mancalaGame.switchPlayer();
        assertEquals(playerTwo, mancalaGame.getActivePlayer());
    }

    @Test
    public void WhenAPlayerMakesMoveWithBowlTwoTheNumberOfStonesInTheFollowingBowlsChange(){
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

        assertEquals(0, playerOne.getStonesPerKalaha());

        // The active player switch to another player
        assertEquals(playerTwo, mancalaGame.getActivePlayer());
    }

    @Test
    public void WhenAPlayerMakesMoveAndEndsInHisKalahaTheActivePlayerIsStillTheSamePlayer(){
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

        assertEquals(1, playerOne.getStonesPerKalaha());

        assertEquals(playerOne, mancalaGame.getActivePlayer());
    }

    @Test
    public void WhenMakingAMoveWithBowlSixTheActivePlayerIsSwitchedAndPlayerTwoGetsToMakeAMove(){

        // PlayerOne makes a move with his bowl six
        mancalaGame.playerMove(6);

        // PlayerOne Board
        assertEquals(4, playerOne.getBowls().get(0).getStonesPerBowl());
        assertEquals(4, playerOne.getBowls().get(1).getStonesPerBowl());
        assertEquals(4, playerOne.getBowls().get(2).getStonesPerBowl());
        assertEquals(4, playerOne.getBowls().get(3).getStonesPerBowl());
        assertEquals(4, playerOne.getBowls().get(4).getStonesPerBowl());
        assertEquals(0, playerOne.getBowls().get(5).getStonesPerBowl());

        assertEquals(1, playerOne.getStonesPerKalaha());

        // PlayerTwo Board
        assertEquals(5, playerTwo.getBowls().get(0).getStonesPerBowl());
        assertEquals(5, playerTwo.getBowls().get(1).getStonesPerBowl());
        assertEquals(5, playerTwo.getBowls().get(2).getStonesPerBowl());
        assertEquals(4, playerTwo.getBowls().get(3).getStonesPerBowl());
        assertEquals(4, playerTwo.getBowls().get(4).getStonesPerBowl());
        assertEquals(4, playerTwo.getBowls().get(5).getStonesPerBowl());

        assertEquals(0, playerTwo.getStonesPerKalaha());

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

        assertEquals(1, playerTwo.getStonesPerKalaha());

        // PlayerTwo is still the active player
        activePlayer = playerTwo;
        assertEquals(activePlayer, mancalaGame.getActivePlayer());
    }

    @Test
    public void WhenAllBowlsOfAPlayerAreEmptyThenAWinnerIsDeclared(){
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


        int playerOneScore = playerOne.getStonesPerKalaha();
        int playerTwoScore = playerTwo.getStonesPerKalaha();
        String formattedString = String.format("playerOne Score: %s \nplayerTwo Score: %s", playerOneScore, playerTwoScore);
        System.out.println(formattedString);

        // Declare who is winner
        assertEquals(playerOne, mancalaGame.getWinner());
    }
}
