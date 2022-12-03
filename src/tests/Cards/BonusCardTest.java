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

class BonusCardTest {

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
    void getRoll200Test() {
        TurnResult expected = new TurnResult((short) 500, true);
        BonusCard bonusC = new BonusCard(CardsValue.BONUS200);
        assertEquals(expected.points, bonusC.getRoll().points);
        assertEquals(expected.isTutto, bonusC.getRoll().isTutto);

    }
    @Test
    void getRoll300Test() {
        TurnResult expected = new TurnResult((short) 600, true);
        BonusCard bonusC = new BonusCard(CardsValue.BONUS300);
        assertEquals(expected.points, bonusC.getRoll().points);
        assertEquals(expected.isTutto, bonusC.getRoll().isTutto);

    }

    @Test
    void getRoll400Test() {
        TurnResult expected = new TurnResult((short) 700, true);
        BonusCard bonusC = new BonusCard(CardsValue.BONUS400);
        assertEquals(expected.points, bonusC.getRoll().points);
        assertEquals(expected.isTutto, bonusC.getRoll().isTutto);

    }
    @Test
    void getRoll500Test() {
        TurnResult expected = new TurnResult((short) 800, true);
        BonusCard bonusC = new BonusCard(CardsValue.BONUS500);
        assertEquals(expected.points, bonusC.getRoll().points);
        assertEquals(expected.isTutto, bonusC.getRoll().isTutto);

    }
    @Test
    void getRoll600Test() {
        TurnResult expected = new TurnResult((short) 900, true);
        BonusCard bonusC = new BonusCard(CardsValue.BONUS600);
        assertEquals(expected.points, bonusC.getRoll().points);
        assertEquals(expected.isTutto, bonusC.getRoll().isTutto);

    }
}