package Dices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiceTest {


    @Test
    void getDiceNumberTest() {
        Dice dice = new Dice();
        assert(
                dice.getDiceNumber() == diceNumber.ONE ||
                dice.getDiceNumber() == diceNumber.TWO ||
                dice.getDiceNumber() == diceNumber.THREE ||
                dice.getDiceNumber() == diceNumber.FOUR ||
                dice.getDiceNumber() == diceNumber.FIVE ||
                dice.getDiceNumber() == diceNumber.SIX
                );
    }

    @Test
    void putAsideTest() {
        Dice dice = new Dice();
        dice.putAside();
        assertTrue(dice.isAside());
    }

    @Test
    void isAsideTest() {
        Dice dice = new Dice();
        assertFalse(dice.isAside());
    }
}