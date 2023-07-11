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
}
