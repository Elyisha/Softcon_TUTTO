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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlusMinusCardTest {

    @BeforeAll
    public static void installMockClasses() {
        new CardMock();
        new CardMockValid();
    }

    public static class CardMock extends MockUp<DecideDice> {
        @Mock
            //mocks decideDice to be a tutto and putting away 6 dices giving 50p each
        short decideDice(List<Dice> dices, boolean fireworks) {
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
        TurnResult expected = new TurnResult((short) 1000, true);
        PlusMinusCard testcard = new PlusMinusCard();
        assertEquals(expected.points, testcard.getRoll().points);
        assertEquals(expected.isTutto, testcard.getRoll().isTutto);
    }
}