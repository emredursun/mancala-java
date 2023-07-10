package mancala.domain;

import java.util.ArrayList;
import java.util.List;

class Player {
    private final Kalaha kalaha;
    private final List<Bowl> bowls;
    private StoneCollectors activeElement;

    public Player(Kalaha playerKalaha) {
        this.kalaha = playerKalaha;
        bowls = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            bowls.add(new Bowl());
        }
    }

    public int getScore() {
        return kalaha.getStonesPerKalaha();
    }

    public List<Bowl> getCups() {
        return bowls;
    }

    public int getNumberOfStones() {
        int numberOfStonesInAllCups = 0;
        for (Bowl bowl : bowls) {
            numberOfStonesInAllCups += bowl.getStonesPerCup();
        }
        return numberOfStonesInAllCups;
    }

    public boolean hasStonesLeft() {
        return getNumberOfStones() > 0;
    }

    public StoneCollectors makeMove(int selectedCupNumber, List<Bowl> opponentBowls) {
        List<StoneCollectors> stoneCollectors = new ArrayList<>(bowls);
        stoneCollectors.add(kalaha);
        stoneCollectors.addAll(opponentBowls);

        Bowl selectedBowl = bowls.get(selectedCupNumber - 1);
        int numberOfStonesSelectedCup = selectedBowl.getStonesPerCup();

        selectedBowl.emptyCup();

        for (int i = selectedCupNumber; i < selectedCupNumber + numberOfStonesSelectedCup; i++) {
            activeElement = stoneCollectors.get(i % stoneCollectors.size());
            activeElement.addOneStone();
        }

        if (activeElement instanceof Kalaha) {
            return activeElement;
        }

        Bowl lastBowl = (Bowl) activeElement;
        if (bowls.contains(lastBowl) && lastBowl.getStonesPerCup() == 1) {
            lastBowl.emptyCup();
            kalaha.addOneStone();
            Bowl opponentsBowl = opponentBowls.get(opponentBowls.size() - bowls.indexOf(lastBowl) - 1);
            for (int i = 0; i < opponentsBowl.getStonesPerCup(); i++) {
                kalaha.addOneStone();
            }
            opponentsBowl.emptyCup();
        }
        return activeElement;
    }
}