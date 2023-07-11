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

        public int getStonesPerKalaha() {
            return kalaha.getStonesPerKalaha();
        }

        public List<Bowl> getBowls() {
            return bowls;
        }

        public int getNumberOfStones() {
            int numberOfStonesInAllBowls = 0;
            for (Bowl bowl : bowls) {
                numberOfStonesInAllBowls += bowl.getStonesPerBowl();
            }
            return numberOfStonesInAllBowls;
        }

        public boolean hasStonesLeft() {
            return getNumberOfStones() > 0;
        }

        public StoneCollectors makeMove(int selectedBowlNumber, List<Bowl> opponentBowls) {
            List<StoneCollectors> stoneCollectors = new ArrayList<>(bowls);
            stoneCollectors.add(kalaha);
            stoneCollectors.addAll(opponentBowls);

            Bowl selectedBowl = bowls.get(selectedBowlNumber - 1);
            int numberOfStonesSelectedCup = selectedBowl.getStonesPerBowl();

            selectedBowl.emptyBowl();

            for (int i = selectedBowlNumber; i < selectedBowlNumber + numberOfStonesSelectedCup; i++) {
                activeElement = stoneCollectors.get(i % stoneCollectors.size());
                activeElement.addOneStone();
            }

            if (activeElement instanceof Kalaha) {
                return activeElement;
            }

            Bowl lastBowl = (Bowl) activeElement;
            if (bowls.contains(lastBowl) && lastBowl.getStonesPerBowl() == 1) {
                lastBowl.emptyBowl();
                kalaha.addOneStone();
                Bowl opponentsBowl = opponentBowls.get(opponentBowls.size() - bowls.indexOf(lastBowl) - 1);
                for (int i = 0; i < opponentsBowl.getStonesPerBowl(); i++) {
                    kalaha.addOneStone();
                }
                opponentsBowl.emptyBowl();
            }
            return activeElement;
        }
    }