import org.game.bowling.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RollTest {

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRoll() {
        Roll.of("");
    }

    @Test
    public void testValidStrikeRollNumber() {
        Roll roll = Roll.of("X");
        assertEquals(10, roll.numberOfPins());
    }

    @Test
    public void testValidSpareRollNumber() {
        Roll roll = Roll.of("/");
        assertEquals(10, roll.numberOfPins());
    }

    @Test
    public void testValidScoreRollNumber() {
        Roll roll = Roll.of("5");
        assertEquals(5, roll.numberOfPins());
    }

    @Test
    public void testValidEmptyRollNumber() {
        Roll roll = Roll.empty();
        assertEquals(0, roll.numberOfPins());
    }
}