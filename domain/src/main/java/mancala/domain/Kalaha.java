package mancala.domain;

class Kalaha implements StoneCollectors {
    private int currentNumberOfStonesInAKalaha;

    public int getStonesPerKalaha() {
        return currentNumberOfStonesInAKalaha;
    }

    @Override
    public void addOneStone() {
        currentNumberOfStonesInAKalaha += 1;
    }

    @Override
    public void addStones(int count) {
        currentNumberOfStonesInAKalaha += count;
    }
}
