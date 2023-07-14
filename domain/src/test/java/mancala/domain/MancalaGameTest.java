package mancala.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MancalaGameTest {
    private MancalaGame mancalaGame;
    private Player playerOne;
    private Player playerTwo;
    private Player activePlayer;

    @BeforeEach
    public void setUp() {
        playerOne = new Player(new Kalaha());
        playerTwo = new Player(new Kalaha());
        mancalaGame = new MancalaGame(playerOne, playerTwo);
        activePlayer = mancalaGame.getActivePlayer();
    }

    @Test
    public void testGetPlayerOne() {
        assertEquals(playerOne, mancalaGame.getPlayerOne());
    }

    @Test
    public void testGetPlayerTwo() {
        assertEquals(playerTwo, mancalaGame.getPlayerTwo());
    }

    @Test
    public void testIsGameEnded() {
        assertFalse(mancalaGame.isGameEnded());
    }

    @Test
    public void testIfPlayerOneIsActivePlayerWhenGameStarts(){
        assertEquals(playerOne, mancalaGame.getActivePlayer());
    }

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
        mancalaGame.getPlayerOne().getBowls().forEach(Bowl::empty);
        assertTrue(mancalaGame.isGameEnded());
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
            assertEquals(4, mancalaGame.getActivePlayer().getBowls().get(i).getStones());
        }

        // After making a move
        mancalaGame.playerMove(2);
        assertEquals(4, activePlayer.getBowls().get(0).getStones());
        assertEquals(0, activePlayer.getBowls().get(1).getStones());
        assertEquals(5, activePlayer.getBowls().get(2).getStones());
        assertEquals(5, activePlayer.getBowls().get(3).getStones());
        assertEquals(5, activePlayer.getBowls().get(4).getStones());
        assertEquals(5, activePlayer.getBowls().get(5).getStones());

        assertEquals(24, playerOne.getTotalStones());

        // The active player switch to another player
        assertEquals(playerTwo, mancalaGame.getActivePlayer());
    }

    @Test
    public void testIfActivePlayerIsSwitchedWhenMoveEndsInOwnBowl(){
//      PlayerOne makes a move by choosing first bowl
        mancalaGame.playerMove(1);

        // PlayerOne Board
        assertEquals(0, activePlayer.getBowls().get(0).getStones());
        assertEquals(5, activePlayer.getBowls().get(1).getStones());
        assertEquals(5, activePlayer.getBowls().get(2).getStones());
        assertEquals(5, activePlayer.getBowls().get(3).getStones());
        assertEquals(5, activePlayer.getBowls().get(4).getStones());
        assertEquals(4, activePlayer.getBowls().get(5).getStones());

        // The active player switch to another player
        assertEquals(playerTwo, mancalaGame.getActivePlayer());
    }

    @Test
    public void testIfActivePlayerIsSwitchedWhenMoveEndsInOpponentBowl(){
        mancalaGame.playerMove(6);

        // PlayerOne Board
        assertEquals(4, activePlayer.getBowls().get(0).getStones());
        assertEquals(4, activePlayer.getBowls().get(1).getStones());
        assertEquals(4, activePlayer.getBowls().get(2).getStones());
        assertEquals(4, activePlayer.getBowls().get(3).getStones());
        assertEquals(4, activePlayer.getBowls().get(4).getStones());
        assertEquals(0, activePlayer.getBowls().get(5).getStones());
        assertEquals(1, activePlayer.getKalaha().getStones());

        // PlayerTwo Board
        assertEquals(5, playerTwo.getBowls().get(0).getStones());
        assertEquals(5, playerTwo.getBowls().get(1).getStones());
        assertEquals(5, playerTwo.getBowls().get(2).getStones());
        assertEquals(4, playerTwo.getBowls().get(3).getStones());
        assertEquals(4, playerTwo.getBowls().get(4).getStones());
        assertEquals(4, playerTwo.getBowls().get(5).getStones());

        // The active player switch to another player
        assertEquals(playerTwo, mancalaGame.getActivePlayer());
    }

    @Test
    public void testIfActivePlayerIsNotSwitchedWhenMoveEndsInKalaha(){
        // Before making a move
        for(int i = 0; i < 6; i++){
            assertEquals(4, activePlayer.getBowls().get(i).getStones());
        }

        // After making move with bowl number 3
        mancalaGame.playerMove(3);
        assertEquals(4, activePlayer.getBowls().get(0).getStones());
        assertEquals(4, activePlayer.getBowls().get(1).getStones());
        assertEquals(0, activePlayer.getBowls().get(2).getStones());
        assertEquals(5, activePlayer.getBowls().get(3).getStones());
        assertEquals(5, activePlayer.getBowls().get(4).getStones());
        assertEquals(5, activePlayer.getBowls().get(5).getStones());
        assertEquals(1, activePlayer.getKalaha().getStones());

        assertEquals(playerOne, mancalaGame.getActivePlayer());
    }

    @Test
    public void testIfWinnerIsDeclaredWhenAllBowlsOfAPlayerAreEmpty(){
        // Updating the number of stone as Zero in the PlayerOne's bowls
        for(int i = 0; i < 6; i++){
            activePlayer.getBowls().get(i).empty();
        }

        activePlayer.getBowls().get(5).addStone();

        // PlayerOne Board
        assertEquals(0, playerOne.getBowls().get(0).getStones());
        assertEquals(0, playerOne.getBowls().get(1).getStones());
        assertEquals(0, playerOne.getBowls().get(2).getStones());
        assertEquals(0, playerOne.getBowls().get(3).getStones());
        assertEquals(0, playerOne.getBowls().get(4).getStones());
        assertEquals(1, playerOne.getBowls().get(5).getStones());

        mancalaGame.playerMove(6);

        // Check if playerOne has no stones in his own bowls
        assertFalse(playerOne.hasStonesLeft());

        int playerOneScore = playerOne.getScore();
        int playerTwoScore = playerTwo.getScore();
        String formattedString = String.format("playerOne Score: %s \nplayerTwo Score: %s", playerOneScore, playerTwoScore);
        System.out.println(formattedString);

        // Declare who is winner
        assertEquals(playerOne, mancalaGame.getWinner());
    }
}