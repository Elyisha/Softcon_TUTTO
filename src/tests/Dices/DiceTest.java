package Dices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DiceTest {
    @Test
    void rollDiceTest() {
        Dice dice = new Dice();
        dice.rollDice();
        assertNotNull(dice);
    }

    @Test
    void getDiceNumberTest() {
        Dice dice = new Dice();
        dice.rollDice();
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