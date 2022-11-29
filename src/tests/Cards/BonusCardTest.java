package Cards;

import Dices.Dice;
import Gameflow.TurnResult;
import Input.DecideDice;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BonusCardTest {

    @BeforeAll
    public static void installMockClasses() {
        new CardMock();
    }

    public static class CardMock extends MockUp<DecideDice> {
        @Mock
            //mocks decideDice to be a tutto and putting away 6 dices giving 50p each
        short decideDice(Dice[] dices, boolean fireworks) {
            for (byte i = 0; i < 6; i++) {dices[i].putAside();}
            return 300;
        }
    }
    @Test
    void getRoll() {
        TurnResult expected = new TurnResult((short) 600, true);
        BonusCard bonus300 = new BonusCard(CardsValue.BONUS300);
        assertEquals(expected.points, bonus300.getRoll().points);
        assertEquals(expected.isTutto, bonus300.getRoll().isTutto);
    }
}