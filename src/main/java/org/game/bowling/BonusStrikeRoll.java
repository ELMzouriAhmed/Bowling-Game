package org.game.bowling;
import static org.game.bowling.Frame.TEN_POINT;

public class BonusStrikeRoll {
    private final Roll firstBonusRoll;
    private final Roll secondBonusRoll;

    public BonusStrikeRoll(String firstBonusRoll, String secondBonusRoll) {
        this.firstBonusRoll = Roll.of(firstBonusRoll);
        this.secondBonusRoll = Roll.of(secondBonusRoll);
    }

    public Roll getFirstBonusRoll() {
        return firstBonusRoll;
    }

    public Roll getSecondBonusRoll() {
        return secondBonusRoll;
    }

    public int getScore() {
        int score = 0;
        if (this.firstBonusRoll.isStrike()) {
            score += TEN_POINT;
        } else {
            score += this.getFirstBonusRoll().numberOfPins();
        }
        if (this.secondBonusRoll.isStrike() ) {
            score += TEN_POINT;
        } else {
            score += this.getSecondBonusRoll().numberOfPins();
        }
        return score;
    }
}
