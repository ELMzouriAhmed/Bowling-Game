package org.game.bowling;

public class Rolls {
    private final Roll firstTry;
    private final Roll secondTry;

    public Rolls(String firstTry, String secondTry) {
        this.firstTry = Roll.of(firstTry);
        this.secondTry = Roll.of (secondTry);
    }

    public Roll getFirstTry() {
        return firstTry;
    }

    public Roll getSecondTry() {
        return secondTry;
    }
}
