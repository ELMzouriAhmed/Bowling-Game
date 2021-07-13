package org.game.bowling;

import java.util.Objects;

public final class Roll {
    private static final String SCORE_SYMBOLS = "(?i)[1-9]{1}|X|/|-";
    private static final String SCORE_NUMBER = "[1-9]{1}";
    private static final String STRIKE_SYMBOL = "x";
    private static final String SPARE_SYMBOL = "/";

    enum RollType {
        NORMAL, SPARE, STRIKE, EMPTY;
    }

    private final int numberOfPins;
    private final RollType rollType;

    private Roll(int numberOfPins, RollType rollType) {
        if (numberOfPins < 0 || numberOfPins > 10) {
            throw new IllegalArgumentException("Invalid roll range");
        }
        this.numberOfPins = numberOfPins;
        this.rollType = rollType;
    }

    public static Roll of(String scoreSymbol) {
        checkPinsSymbol(scoreSymbol);
        if (scoreSymbol == null) {
            return empty();
        }
        if (scoreSymbol.matches(SCORE_NUMBER)) {
            return new Roll(Integer.parseInt(scoreSymbol), RollType.NORMAL);
        }
        if (scoreSymbol.equalsIgnoreCase(STRIKE_SYMBOL)) {
            return new Roll(10, RollType.STRIKE);
        }
        if (scoreSymbol.equals(SPARE_SYMBOL)) {
            return new Roll(10, RollType.SPARE);
        }
        return empty();
    }
    public static Roll ofSecondStrikeTry(String scoreSymbol) {
        return new Roll(0, RollType.STRIKE);
    }

    public static Roll empty() {
        return new Roll(0, RollType.EMPTY);
    }

    public int numberOfPins() {
        return numberOfPins;
    }

    boolean isEmpty() {
        return rollType == RollType.EMPTY;
    }

    private static void checkPinsSymbol(String scoreSymbol) {
        if (null != scoreSymbol && !scoreSymbol.matches(SCORE_SYMBOLS)) {
            throw new IllegalArgumentException("Invalid roll symbol");
        }
    }

    public boolean isSpare() {
        return rollType == RollType.SPARE;
    }

    public boolean isStrike() {
        return rollType == RollType.STRIKE;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Roll that = (Roll) obj;
        return this.numberOfPins == that.numberOfPins &&
                Objects.equals(this.rollType, that.rollType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfPins, rollType);
    }

    @Override
    public String toString() {
        return "Roll[" +
                "numberOfPins=" + numberOfPins + ", " +
                "rollType=" + rollType + ']';
    }

}
