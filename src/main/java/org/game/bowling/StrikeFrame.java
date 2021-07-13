package org.game.bowling;

import java.util.List;
import java.util.Scanner;

public class StrikeFrame extends Frame {
    private final Rolls rolls;

    private BonusStrikeRoll bonusStrikeRoll;

    public void setBonusStrikeRoll(BonusStrikeRoll bonusStrikeRoll) {
        this.bonusStrikeRoll = bonusStrikeRoll;
    }

    public StrikeFrame(Rolls rolls) {
        this.rolls = rolls;
    }

    public Rolls getRolls() {
        return rolls;
    }

    public boolean getCheckRoll() {
        return checkFirstTry(this.rolls.getFirstTry()) & checkSecondTry(this.rolls.getSecondTry());
    }

    public static StrikeFrame checkFrame(String firstTry, String secondTry, int index) {
        Roll firstRoll =  Roll.of(firstTry);
        if (checkFirstTry(firstRoll)  && index == LAST_FRAME) {
            return addStrikeWithBonus(new StrikeFrame(new  Rolls (firstTry, secondTry)));
        } else if (checkFirstTry(firstRoll)  && index < LAST_FRAME) {
            return new StrikeFrame(new  Rolls (firstTry, secondTry));
        } else {
            throw new IllegalArgumentException("Invalid strike roll");
        }
    }

    private static StrikeFrame addStrikeWithBonus(StrikeFrame strikeFrame) {
        Scanner clavier = new Scanner(System.in);
        System.out.print("saisir votre premiere Bonus shot : ");
        String firstBonusTry = clavier.nextLine();
        System.out.print("saisir votre desieme Bonus shot :  ");
        String secondBonusTry = clavier.nextLine();
        strikeFrame.setBonusStrikeRoll(new  BonusStrikeRoll (firstBonusTry, secondBonusTry));
        return strikeFrame;
    }

    public static boolean checkFirstTry( Roll firstTry) {
        return firstTry.isStrike();
    }

    public static boolean checkSecondTry( Roll secondTry) {
        return secondTry.isEmpty();
    }

    @Override
    public int getFrameScore(List< Frame> frames, int index) {
        if (index >= 1) return frames.get(index - 1).getFrameScore(frames, index - 1) + getcheckStrike(frames, index);
        return TEN_POINT + getcheckStrike(frames, index);

    }

    private int getcheckStrike(List< Frame> frames, int index) {
        int score = TEN_POINT;
        if (index + 1 < frames.size() && isStrike(frames, index + 1)) {
            score += TEN_POINT;
        } else if (index + 1 < frames.size()) {
            return score += frames.get(index + 1).nextFrameScore(frames, index + 1);
        }
        if (index + 2 < frames.size() && isStrike(frames, index + 2)) {
            score += TEN_POINT;
        } else if (index + 2 < frames.size()) {
            return score += frames.get(index + 2).getThirdStrikeScore(frames, index + 2);
        }
        if (index == LAST_FRAME) score += this.bonusStrikeRoll.getScore();
        return score;
    }

    private boolean isStrike(List< Frame> frames, int index) {
        return frames.get(index).getFrameRoll().getFirstTry().isStrike();
    }

    @Override
    public  Rolls getFrameRoll() {
        return this.getRolls();
    }

    @Override
    public int nextFrameScore(List< Frame> frames, int index) {
        return TEN_POINT;
    }

}
