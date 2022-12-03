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

class FireworksCardTest {

    @BeforeAll
    public static void installMockClasses() {
        new Cards.FireworksCardTest.CardMock();
        new Cards.FireworksCardTest.CardMockValid();
    }

    public static class CardMock extends MockUp<DecideDice> {
        @Mock
            //mocks decideDice to be a tutto and putting away 6 dices giving 50p each
        short decideDice(List<Dice> dices, boolean firework) {
            for (byte i = 0; i < 6; i++) {dices.get(i).putAside();}
            return 0;
        }

    }
    public static class CardMockValid extends MockUp<ValidDice> {
        @Mock
        short countPoints(Dice[] dices) {
            return 0;
        }
    }

        @Test
        void getRollTest() {
            TurnResult expected = new TurnResult((short) 0, false);
            FireworksCard testcard = new FireworksCard();
            assertEquals(expected.points, testcard.getRoll().points);
            assertEquals(expected.isTutto, testcard.getRoll().isTutto);
        }
    }
