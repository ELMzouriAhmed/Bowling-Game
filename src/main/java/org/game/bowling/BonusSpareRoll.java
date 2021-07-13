package org.game.bowling;

import static org.game.bowling.Frame.TEN_POINT;

public class BonusSpareRoll {
    private final Roll bonusSpare;

    public BonusSpareRoll(String bonusSpare) {
        this.bonusSpare = Roll.of(bonusSpare);
    }

    public Roll getBonusSpare() {
        return bonusSpare;
    }

    public int getScore() {
        int score = 0;
        if (bonusSpare.isStrike()) {
            score += TEN_POINT;
        } else {
            score += this.getBonusSpare().numberOfPins();
        }
        return score;
    }
}
