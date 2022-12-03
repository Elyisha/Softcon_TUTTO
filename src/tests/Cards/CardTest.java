package Cards;

import Dices.Dice;
import Dices.ValidDice;
import Input.DecideDice;
import Gameflow.TurnResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import mockit.*;

class CardTest {

    @BeforeAll
    public static void installMockClasses() {
        new CardMock();
        new CardMockValid();
    }

    public static class CardMock extends MockUp<DecideDice> {
        @Mock //mocks decideDice to be a tutto and putting away 6 dices giving 50p each
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
        ArrayList<Dice> dices= new ArrayList<Dice>(); //stores the dices
        for (byte i = 0; i < 6; i++) dices.add(new Dice()); //instantiate the dices (doesn't roll them!)
        ArrayList<Dice> countDices = new ArrayList<>();
        Card.rollDisplayCount(dices, countDices);
        //make sure it has been rolled
        assertNotNull(dices.get(0));
        assertNotNull(dices.get(1));
        assertNotNull(dices.get(2));
        assertNotNull(dices.get(3));
        assertNotNull(dices.get(4));
        assertNotNull(dices.get(5));
    }
    @Test
    void rollDisplayCountTestPutAside() {
        ArrayList<Dice> dices= new ArrayList<Dice>(); //stores the dices
        for (byte i = 0; i < 6; i++) dices.add(new Dice()); //instantiate the dices (doesn't roll them!)
        ArrayList<Dice> countDices = new ArrayList<>();
        dices.get(2).putAside();
        Card.rollDisplayCount(dices, countDices);
        //make sure dices[2] was not put into the countDices ArrayList
        assertEquals(5,countDices.size());
    }
    @Test
    void tuttoCheckerTest() {
        ArrayList<Dice> dices= new ArrayList<Dice>(); //stores the dices
        for (byte i = 0; i < 6; i++) {dices.add(new Dice());dices.get(i).putAside();} //instantiate the dices (doesn't roll them!)
        assertTrue(Card.tuttoChecker(dices));
    }
}