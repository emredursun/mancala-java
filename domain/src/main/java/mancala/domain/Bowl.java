package mancala.domain;


class Bowl implements StoneCollectors {
    private int currentNumberOfStonesInABowl;

    public Bowl() {
        currentNumberOfStonesInABowl = 4;
    }

    public int getStonesPerBowl() {
        return currentNumberOfStonesInABowl;
    }

    public void emptyBowl() {
        currentNumberOfStonesInABowl = 0;
    }

    @Override
    public void addOneStone() {
        currentNumberOfStonesInABowl += 1;
    }

    @Override
    public void addStones(int count) {
        currentNumberOfStonesInABowl += count;
    }
}
