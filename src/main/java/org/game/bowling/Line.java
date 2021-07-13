package org.game.bowling;

import java.util.List;

public class Line {

    private final List<Frame> frames;

    public Line(List<Frame> frames) {
        this.frames = frames;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    public int getScore() {
        int index = 0;
        for (; index < this.getFrames().size() - 1; index++) {
            Frame frame = getFrames().get(index);
            frame.getFrameScore(getFrames(), index);
        }
        return getFrames().get(index).getFrameScore(getFrames(), index);
    }
}
