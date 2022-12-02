package Cards;

import Dices.Dice;
import Dices.ValidDice;
import Gameflow.TurnResult;
import Input.DecideDice;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TwoTimesCardTest {



    @BeforeAll
    public static void installMockClasses() {
        new CardMock();
        new CardMockValid();
    }

    public static class CardMock extends MockUp<DecideDice> {
        @Mock
            //mocks decideDice to be a tutto and putting away 6 dices giving 50p each
        short decideDice(ArrayList<Dice> dices, boolean fireworks) {
            for (byte i = 0; i < 6; i++) {dices.get(i).putAside();}
            return 300;
        }
    }

    public static class CardMockValid extends MockUp<ValidDice> {
        @Mock
        public static short countPoints(ArrayList<Dice> dices) {
            return 300;
        }
    }
    @Test
    void getRollTest() {
        TurnResult expected = new TurnResult((short) 600, true);
        TwoTimesCard testcard = new TwoTimesCard();
        assertEquals(expected.points, testcard.getRoll().points);
        assertEquals(expected.isTutto, testcard.getRoll().isTutto);
    }

}