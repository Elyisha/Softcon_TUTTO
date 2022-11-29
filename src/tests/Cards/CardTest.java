package Cards;

import Dices.Dice;
import Input.DecideDice;
import Gameflow.TurnResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import mockit.*;

class CardTest {

    @BeforeAll
    public static void installMockClasses() {
        new CardMock();
    }

    public static class CardMock extends MockUp<DecideDice> {
        @Mock //mocks decideDice to be a tutto and putting away 6 dices giving 50p each
        short decideDice(Dice[] dices, boolean fireworks) {
            for (byte i = 0; i < 6; i++) {dices[i].putAside();}
            return 300;
        }
    }

    @Test
    void getValueTest() {
        BonusCard testBonusCard = new BonusCard(CardsValue.BONUS200);
        assertEquals(CardsValue.BONUS200,testBonusCard.getValue());
    }


    @Test
    void getAbstractRollTest() {
        TurnResult expected = new TurnResult((short) 300, true);
        assertEquals(expected.points, Card.getAbstractRoll().points);
        assertEquals(expected.isTutto, Card.getAbstractRoll().isTutto);
    }

    @Test
    void rollDisplayCountTestRoll() {
        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < 6; i++) {dices[i] = new Dice();} //instantiate the dices (doesn't roll them!)
        ArrayList<Dice> countDices = new ArrayList<>();
        Card.rollDisplayCount(dices, countDices);
        //make sure it has been rolled
        assertNotNull(dices[0]);
        assertNotNull(dices[1]);
        assertNotNull(dices[2]);
        assertNotNull(dices[3]);
        assertNotNull(dices[4]);
        assertNotNull(dices[5]);
    }
    @Test
    void rollDisplayCountTestPutAside() {
        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < 6; i++) {dices[i] = new Dice();} //instantiate the dices (doesn't roll them!)
        ArrayList<Dice> countDices = new ArrayList<>();
        dices[2].putAside();
        Card.rollDisplayCount(dices, countDices);
        //make sure dices[2] was not put into the countDices ArrayList
        assertEquals(5,countDices.size());
    }
    @Test
    void tuttoCheckerTest() {
        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < 6; i++) {dices[i] = new Dice();dices[i].putAside();} //instantiate the dices (doesn't roll them!)
        assertTrue(Card.tuttoChecker(dices));
    }
}