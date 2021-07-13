import org.game.bowling.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BowlingTest {

    @Test
    public void chreckRollValue() {
        Line line = new Line(Collections.singletonList(new SpareFrame(new Rolls("8", "/"))));
        SpareFrame spareFrame = (SpareFrame) line.getFrames().get(0);
        assertEquals (8, spareFrame.getRolls().getFirstTry().numberOfPins());
        assertTrue(spareFrame.getRolls().getSecondTry().isSpare());
        assertEquals(10, spareFrame.getRolls().getSecondTry().numberOfPins());
    }

    @Test
    public void chreckSpareRoll() {
        Line line = new Line(Collections.singletonList(new SpareFrame(new Rolls("8", "/"))));
        assertTrue(line.getFrames().get(0).getCheckRoll());
    }

    @Test
    public void chreckStrikeRoll() {
        Line line = new Line(Collections.singletonList(new StrikeFrame(new Rolls("x", null))));
        assertTrue(line.getFrames().get(0).getCheckRoll());
    }

    @Test
    public void chreckNormalRoll() {
        Line line = new Line(Arrays.asList(new OpenFrame(new Rolls("9", "-"))));
        assertTrue(line.getFrames().get(0).getCheckRoll());
    }

    @Test
    public void chreckFrameNumbers() {
        Line line = getGameWithSpare();
        assertEquals(3, line.getFrames().size());
    }

    @Test
    public void checkNormalScore() {
        Line line = getNormalGame();
        assertEquals(27, line.getScore());
    }

    @Test
    public void checkSpareScore() {
        Line line = getGameWithSpare();
        assertEquals(44, line.getScore());
    }

    @Test
    public void checkStrikeScore() {
        Line line = getGameWithStrike();
        assertEquals(119, line.getScore());
    }

    @Test
    public void checkStrikeScore2() {
        Line line = getGameWithStrike2();
        assertEquals(58, line.getScore());
    }

    @Test
    public void checkComplixFrameScore() {
        Line line = getGameScoreWithStrikeInLastFrame();
        assertEquals(167, line.getScore());
    }

    @Test
    public void checkComplixFrameScore2() {
        Line line = getGameScoreWithSpareInLastFrame();
        assertEquals(153, line.getScore());
    }

    @Test
    public void checkStrikeFrameScore() {
        Line line = getGameScoreWithStrikeFrame();
        assertEquals(300, line.getScore());
    }

    private Line getGameScoreWithStrikeFrame() {
        StrikeFrame strikeFrame = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame1 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame2 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame3 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame4 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame5 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame6 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame7 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame8 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame9 = new StrikeFrame(new Rolls("X", "-"));
        strikeFrame9.setBonusStrikeRoll(new BonusStrikeRoll("X", "X"));


        List<StrikeFrame> strikeFrames = new ArrayList(Arrays.asList(strikeFrame, strikeFrame1, strikeFrame2, strikeFrame3,
                strikeFrame4, strikeFrame5, strikeFrame6, strikeFrame7, strikeFrame8, strikeFrame9));


        List<Frame> frames = new ArrayList();


        frames.addAll(strikeFrames);


        return new Line(frames);
    }

    private Line getGameScoreWithSpareInLastFrame() {
        StrikeFrame strikeFrame = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame1 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame2 = new StrikeFrame(new Rolls("X", "-"));

        OpenFrame openFrame = new OpenFrame(new Rolls("5", "4"));
        OpenFrame openFrame1 = new OpenFrame(new Rolls("5", "3"));
        OpenFrame openFrame2 = new OpenFrame(new Rolls("7", "-"));
        OpenFrame openFrame3 = new OpenFrame(new Rolls("4", "5"));

        SpareFrame spareFrame = new SpareFrame(new Rolls("4", "/"));
        SpareFrame spareFrame1 = new SpareFrame(new Rolls("6", "/"));

        SpareFrame spareFrame2 = new SpareFrame(new Rolls("7", "/"));
        spareFrame2.setBonusSpareRoll(new BonusSpareRoll("6"));


        List<SpareFrame> spareFrames = new ArrayList(Arrays.asList(spareFrame));
        List<SpareFrame> spareFrames1 = new ArrayList(Arrays.asList(spareFrame1));

        List<StrikeFrame> strikeFrames = new ArrayList(Arrays.asList(strikeFrame, strikeFrame1, strikeFrame2));
        List<OpenFrame> openFrames = new ArrayList(Arrays.asList(openFrame));
        List<OpenFrame> openFrames1 = new ArrayList(Arrays.asList(openFrame2));
        List<OpenFrame> openFrames2 = new ArrayList(Arrays.asList(openFrame1));
        List<OpenFrame> openFrames3 = new ArrayList(Arrays.asList(openFrame3));

        List<Frame> frames = new ArrayList();

        frames.addAll(openFrames);
        frames.addAll(spareFrames);
        frames.addAll(openFrames1);
        frames.addAll(strikeFrames);
        frames.addAll(openFrames2);
        frames.addAll(spareFrames1);
        frames.addAll(openFrames3);
        frames.add(spareFrame2);

        return new Line(frames);
    }

    private Line getGameScoreWithStrikeInLastFrame() {
        StrikeFrame strikeFrame = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame1 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame2 = new StrikeFrame(new Rolls("X", "-"));

        StrikeFrame strikeFrame3 = new StrikeFrame(new Rolls("X", "-"));
        strikeFrame3.setBonusStrikeRoll(new BonusStrikeRoll("X", "X"));


        OpenFrame openFrame = new OpenFrame(new Rolls("5", "4"));
        OpenFrame openFrame1 = new OpenFrame(new Rolls("5", "3"));
        OpenFrame openFrame2 = new OpenFrame(new Rolls("7", "-"));
        OpenFrame openFrame3 = new OpenFrame(new Rolls("4", "5"));

        SpareFrame spareFrame = new SpareFrame(new Rolls("4", "/"));
        SpareFrame spareFrame1 = new SpareFrame(new Rolls("6", "/"));


        List<SpareFrame> spareFrames = new ArrayList(Arrays.asList(spareFrame));
        List<SpareFrame> spareFrames1 = new ArrayList(Arrays.asList(spareFrame1));

        List<StrikeFrame> strikeFrames = new ArrayList(Arrays.asList(strikeFrame, strikeFrame1, strikeFrame2));
        List<OpenFrame> openFrames = new ArrayList(Arrays.asList(openFrame));
        List<OpenFrame> openFrames1 = new ArrayList(Arrays.asList(openFrame2));
        List<OpenFrame> openFrames2 = new ArrayList(Arrays.asList(openFrame1));
        List<OpenFrame> openFrames3 = new ArrayList(Arrays.asList(openFrame3));

        List<Frame> frames = new ArrayList();

        frames.addAll(openFrames);
        frames.addAll(spareFrames);
        frames.addAll(openFrames1);
        frames.addAll(strikeFrames);
        frames.addAll(openFrames2);
        frames.addAll(spareFrames1);
        frames.addAll(openFrames3);
        frames.add(strikeFrame3);

        return new Line(frames);
    }

    private Line getGameWithStrike2() {
        StrikeFrame strikeFrame = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame1 = new StrikeFrame(new Rolls("X", "-"));


        OpenFrame openFrame = new OpenFrame(new Rolls("6", "2"));
        OpenFrame openFrame1 = new OpenFrame(new Rolls("1", "4"));
        OpenFrame openFrame2 = new OpenFrame(new Rolls("6", "3"));

        List<StrikeFrame> strikeFrames = new ArrayList(Arrays.asList(strikeFrame, strikeFrame1));
        List<OpenFrame> openFrames = new ArrayList(Arrays.asList(openFrame));
        List<OpenFrame> openFrames1 = new ArrayList(Arrays.asList(openFrame1, openFrame2));

        List<Frame> frames = new ArrayList();
        frames.addAll(openFrames);
        frames.addAll(strikeFrames);
        frames.addAll(openFrames1);


        return new Line(frames);
    }

    private Line getGameWithStrike() {
        StrikeFrame strikeFrame = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame1 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame2 = new StrikeFrame(new Rolls("X", "-"));
        StrikeFrame strikeFrame3 = new StrikeFrame(new Rolls("X", "-"));

        OpenFrame openFrame = new OpenFrame(new Rolls("7", "1"));
        OpenFrame openFrame1 = new OpenFrame(new Rolls("5", "3"));
        OpenFrame openFrame2 = new OpenFrame(new Rolls("6", "2"));
        OpenFrame openFrame3 = new OpenFrame(new Rolls("5", "1"));

        List<StrikeFrame> strikeFrames = new ArrayList(Arrays.asList(strikeFrame, strikeFrame1, strikeFrame2));
        List<StrikeFrame> strikeFrames1 = new ArrayList(Arrays.asList(strikeFrame3));
        List<OpenFrame> openFrames = new ArrayList(Arrays.asList(openFrame));
        List<OpenFrame> openFrames1 = new ArrayList(Arrays.asList(openFrame1, openFrame2));
        List<OpenFrame> openFrames2 = new ArrayList(Arrays.asList(openFrame3));

        List<Frame> frames = new ArrayList();
        frames.addAll(openFrames);
        frames.addAll(strikeFrames);
        frames.addAll(openFrames1);
        frames.addAll(strikeFrames1);
        frames.addAll(openFrames2);

        return new Line(frames);
    }

    private Line getGameWithSpare() {
        SpareFrame spareFrame = new SpareFrame(new Rolls("9", "/"));
        SpareFrame spareFrame1 = new SpareFrame(new Rolls("7", "/"));

        OpenFrame openFrame = new OpenFrame(new Rolls("7", "1"));

        List<SpareFrame> spareFrames = new ArrayList(Arrays.asList(spareFrame));
        List<SpareFrame> spareFrames1 = new ArrayList(Arrays.asList(spareFrame1));
        List<OpenFrame> openFrames = new ArrayList(Arrays.asList(openFrame));

        List<Frame> frames = new ArrayList();
        //frames.addAll ( strikeFrames );
        frames.addAll(spareFrames1);
        frames.addAll(spareFrames);
        frames.addAll(openFrames);

        return new Line(frames);
    }

    private Line getNormalGame() {
        OpenFrame openFrame = new OpenFrame(new Rolls("7", "1"));
        OpenFrame openFrame1 = new OpenFrame(new Rolls("6", "4"));
        OpenFrame openFrame2 = new OpenFrame(new Rolls("6", "3"));
        List<OpenFrame> openFrames = new ArrayList(Arrays.asList(openFrame, openFrame1, openFrame2));
        List<Frame> frames = new ArrayList();
        frames.addAll(openFrames);
        return new Line(frames);
    }
}
