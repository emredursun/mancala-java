package mancala.domain;

public class Bowl implements StoneCollector {
    private int stones;

    public Bowl() {
        this.stones = 4;
    }

    public int getStones() {
        return stones;
    }

    public void empty() {
        stones = 0;
    }

    public void addStone() {
        stones++;
    }
}
