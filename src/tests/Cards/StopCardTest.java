package Cards;

import Gameflow.TurnResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StopCardTest {

    @Test
    void getRollTest() {
        TurnResult expected = new TurnResult((short) 0, false);
        StopCard testcard = new StopCard();
        assertEquals(expected.points, testcard.getRoll().points);
        assertEquals(expected.isTutto, testcard.getRoll().isTutto);
    }
}