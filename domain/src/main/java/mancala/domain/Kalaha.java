package mancala.domain;


public class Kalaha implements StoneCollector {
    private int stones;

    public int getStones() {
        return stones;
    }

    public void addStone() {
        stones++;
    }

    public void addStones(int count) {
        stones += count;
    }
}
