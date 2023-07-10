package mancala.domain;


class Bowl implements StoneCollectors {
    private int currentNumberOfStonesInACup;

    public Bowl() {
        currentNumberOfStonesInACup = 4;
    }

    public int getStonesPerCup() {
        return currentNumberOfStonesInACup;
    }

    public void emptyCup() {
        currentNumberOfStonesInACup = 0;
    }

    @Override
    public void addOneStone() {
        currentNumberOfStonesInACup += 1;
    }
}
