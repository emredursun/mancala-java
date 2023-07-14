package mancala.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private final Kalaha kalaha;
    private final List<Bowl> bowls;

    public Player(Kalaha kalaha) {
        this.kalaha = kalaha;
        this.bowls = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            bowls.add(new Bowl());
        }
    }

    public int getScore() {
        return kalaha.getStones();
    }

    public Kalaha getKalaha() {
        return kalaha;
    }

    public List<Bowl> getBowls() {
        return Collections.unmodifiableList(bowls);
    }

    public int getTotalStones() {
        return bowls.stream().mapToInt(Bowl::getStones).sum();
    }

    public boolean hasStonesLeft() {
        return getTotalStones() > 0;
    }

    public StoneCollector makeMove(int selectedBowlNumber, List<Bowl> opponentBowls) {

        List<StoneCollector> stoneCollectors = new ArrayList<>(bowls);
        stoneCollectors.add(kalaha);
        stoneCollectors.addAll(opponentBowls);

        Bowl selectedBowl = bowls.get(selectedBowlNumber - 1);
        int numberOfStonesSelectedBowl = selectedBowl.getStones();

        selectedBowl.empty();

        StoneCollector lastElement = null;
        for (int i = selectedBowlNumber; i < selectedBowlNumber + numberOfStonesSelectedBowl; i++) {

            int index = i % stoneCollectors.size();
            StoneCollector collector = stoneCollectors.get(index);
            collector.addStone();
            lastElement = collector;
        }

        if (lastElement instanceof Kalaha) {
            return lastElement;
        }

        Bowl lastBowl = (Bowl) lastElement;
        if (bowls.contains(lastBowl) && lastBowl.getStones() == 1) {
            lastBowl.empty();
            kalaha.addStone();

            int opponentIndex = opponentBowls.size() - bowls.indexOf(lastBowl) - 1;
            Bowl opponentsBowl = opponentBowls.get(opponentIndex);
            int stones = opponentsBowl.getStones();

            kalaha.addStones(stones);
            opponentsBowl.empty();
        }

        return lastElement;
    }
}
