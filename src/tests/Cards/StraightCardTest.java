package Cards;

import Dices.Dice;
import Gameflow.TurnResult;
import Input.DecideDice;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StraightCardTest {


    @BeforeAll
    public static void installMockClasses() {
        new Cards.StraightCardTest.CardMock();
    }

    public static class CardMock extends MockUp<DecideDice> {
        @Mock
            //mocks decideDice to be a tutto and putting away 6 dices giving 50p each
        short straightDecideDice(ArrayList<Dice> dices) {
            for (byte i = 0; i < 6; i++) {dices.get(i).putAside();}
            return 300;
        }
    }

    @Test
    void getRollTest() {
        TurnResult expected = new TurnResult((short) 2000, true);
        StraightCard testcard = new StraightCard();
        assertEquals(expected.points, testcard.getRoll().points);
        assertEquals(expected.isTutto, testcard.getRoll().isTutto);
    }
}