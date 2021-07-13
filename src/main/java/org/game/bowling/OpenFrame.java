package org.game.bowling;

import java.util.List;

public class OpenFrame extends Frame {

    private final Rolls rolls;

    public OpenFrame(Rolls rolls) {
        this.rolls = rolls;
    }

    public Rolls getRolls() {
        return rolls;
    }

    public boolean getCheckRoll() {
        return checkFirstTry(this.rolls.getFirstTry()) & checkSecondTry(this.rolls.getSecondTry());
    }

    public static boolean checkFirstTry(Roll firstTry) {
        return firstTry.isEmpty() || firstTry.numberOfPins() >= 0 && firstTry.numberOfPins() <= LAST_FRAME;
    }

    public static boolean checkSecondTry(Roll secondTry) {
        return secondTry.isEmpty() || secondTry.numberOfPins() >= 0 && secondTry.numberOfPins() <= LAST_FRAME;
    }

    @Override
    public int getFrameScore(List<Frame> frames, int index) {
        if (index >= 1 && rolls.getSecondTry().isEmpty()) {
            return (frames.get(index - 1).getFrameScore(frames, index - 1)) + rolls.getFirstTry().numberOfPins();
        } else if (index >= 1) {
            return (frames.get(index - 1).getFrameScore(frames, index - 1)) + sumOfRolls();
        } else if (index == 0 && rolls.getSecondTry().isEmpty()) {
            return rolls.getFirstTry().numberOfPins();
        }
        return sumOfRolls();
    }

    private int sumOfRolls() {
        return rolls.getFirstTry().numberOfPins() + rolls.getSecondTry().numberOfPins();
    }

    @Override
    public Rolls getFrameRoll() {
        return this.getRolls();
    }

    @Override
    public int nextFrameScore(List<Frame> frames, int index) {
        return this.getFrameRoll().getFirstTry().numberOfPins()
            + this.getFrameRoll().getSecondTry().numberOfPins();
    }

    public static Frame checkFrame(String firstTry, String secondTry) {
        Roll firstRoll = Roll.of(firstTry);
        Roll secondRoll = Roll.of(secondTry);
        if (checkFirstTry(firstRoll) && checkSecondTry(secondRoll)) {
            return new OpenFrame(new Rolls (firstTry, secondTry));
        } else {
            throw new IllegalArgumentException("Invalid Normal roll");
        }
    }
}
