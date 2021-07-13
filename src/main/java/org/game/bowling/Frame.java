package org.game.bowling;

import java.util.List;

public abstract class Frame {
    protected static final int LAST_FRAME = 9;
    protected static final int TEN_POINT = 10;

    public abstract boolean getCheckRoll();

    public abstract int getFrameScore(List<Frame> frames, int index);

    public abstract Rolls getFrameRoll();

    public abstract int nextFrameScore(List<Frame> frames, int index);

    protected int getThirdStrikeScore(List<Frame> frames, int index) {
        return frames.get(index).getFrameRoll().getFirstTry().numberOfPins();
    }
}
