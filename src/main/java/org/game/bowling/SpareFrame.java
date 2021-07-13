package org.game.bowling;

import java.util.List;
import java.util.Scanner;

public class SpareFrame extends Frame {
    private final Rolls rolls;

    private BonusSpareRoll bonusSpareRoll;

    public void setBonusSpareRoll(BonusSpareRoll bonusSpareRoll) {
        this.bonusSpareRoll = bonusSpareRoll;
    }

    public SpareFrame(Rolls rolls) {
        this.rolls = rolls;
    }

    public Rolls getRolls() {
        return rolls;
    }

    public boolean getCheckRoll() {
        return checkFirstTry(this.rolls.getFirstTry()) & checkSecondTry(this.rolls.getSecondTry());
    }

    public static boolean checkFirstTry(Roll firstTry) {
        int numberOfPins = firstTry.numberOfPins();
        return numberOfPins >= 0 && numberOfPins <= LAST_FRAME;
    }

    public static boolean checkSecondTry(Roll secondTry) {
        return secondTry.isSpare();
    }

    @Override
    public int getFrameScore(List<Frame> frames, int index) {
        if (index >= 1 && index < frames.size() - 1) {
            return frames.get(index - 1).getFrameScore(frames, index - 1) + TEN_POINT
                    + frames.get(index + 1).getFrameRoll().getFirstTry().numberOfPins();
        }
        if (index == LAST_FRAME) {
            return frames.get(index - 1).getFrameScore(frames, index - 1) + TEN_POINT + bonusSpareRoll.getScore();
        }
        return TEN_POINT + frames.get(index + 1).getFrameRoll().getFirstTry().numberOfPins();
    }

    @Override
    public Rolls getFrameRoll() {
        return this.getRolls();
    }

    @Override
    public int nextFrameScore(List<Frame> frames, int index) {
        return TEN_POINT + frames.get(index + 1).getFrameRoll().getFirstTry().numberOfPins();
    }

    public static SpareFrame checkFrame(String firstTry, String secondTry, int index) {
        Roll firstRoll = Roll.of(firstTry);
        Roll secondRoll = Roll.of(secondTry);
        if (checkFirstTry(firstRoll) && checkSecondTry(secondRoll) && index == LAST_FRAME) {
            return addSpareWithBonus(new SpareFrame(new Rolls (firstTry, secondTry)));
        } else if (checkFirstTry(firstRoll) && checkSecondTry(secondRoll) && index < LAST_FRAME) {
            return new SpareFrame(new Rolls (firstTry, secondTry));
        } else {
            throw new IllegalArgumentException("Invalid strike roll");
        }
    }

    private static SpareFrame addSpareWithBonus(SpareFrame spareFrame) {
        Scanner clavier = new Scanner(System.in);
        System.out.print("saisir votre Bonus shot : ");
        String firstBonusTry = clavier.nextLine();
        spareFrame.setBonusSpareRoll(new BonusSpareRoll(firstBonusTry));
        return spareFrame;
    }
}
