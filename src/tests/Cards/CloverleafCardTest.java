package Cards;

import Dices.Dice;
import Gameflow.TurnResult;
import Input.DecideDice;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloverleafCardTest {

    @BeforeAll
    public static void installMockClasses() {
        new Cards.CloverleafCardTest.CardMock();
    }

    public static class CardMock extends MockUp<DecideDice> {
        @Mock
            //mocks decideDice to be a tutto and putting away 6 dices giving 50p each
        short decideDice(Dice[] dices, boolean fireworks) {
            for (byte i = 0; i < 6; i++) {
                dices[i].putAside();
            }
            return 300;
        }
    }

    @Test
    void getRollTest() {
        TurnResult expected = new TurnResult((short) 0, true);
        CloverleafCard testcard = new CloverleafCard();
        assertEquals(expected.points, testcard.getRoll().points);
        assertEquals(expected.isTutto, testcard.getRoll().isTutto);
    }
}