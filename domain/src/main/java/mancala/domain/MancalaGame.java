package mancala.domain;

public class MancalaGame {
    private final Player playerOne;
    private final Player playerTwo;
    private Player activePlayer;

    public MancalaGame(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.activePlayer = playerOne;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public boolean isGameEnded() {
        return !(playerOne.hasStonesLeft() && playerTwo.hasStonesLeft());
    }

    public Player getWinner() {
        if (!isGameEnded()) {
            return null;
        } else if (playerOne.getScore() > playerTwo.getScore()) {
            return playerOne;
        } else if (playerTwo.getScore() > playerOne.getScore()) {
            return playerTwo;
        } else {
            return null;
        }
    }

    public Player switchPlayer() {
        activePlayer = (activePlayer == playerOne) ? playerTwo : playerOne;
        return activePlayer;
    }

    public void playerMove(int bowlNumber) {

        if (isGameEnded()) {
            return;
        }

        StoneCollector activeElement = getActivePlayer().makeMove(bowlNumber, switchPlayer().getBowls());

        if (activeElement instanceof Kalaha) {
            switchPlayer();
        }
    }
}
