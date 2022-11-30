package Dices;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static Dices.ValidDice.hasNoDuplicates;
import static org.junit.jupiter.api.Assertions.*;

import static Dices.ValidDice.isValidGuess;

class ValidDiceTest {

    public static Dice[] SetupDiceArray() {
        Dice[] dices = new Dice[6];
        for(int i = 0; i < 6; ++i) {
            dices[i] = new Dice();
            if(i == 0)
                do {
                    dices[i].rollDice();
                } while(dices[i].getDiceNumber().ordinal() != 2);
            else if(i == 4)
                do {
                    dices[i].rollDice();
                } while(dices[i].getDiceNumber().ordinal() != 3);
            else if(i == 5)
                do {
                    dices[i].rollDice();
                } while(dices[i].getDiceNumber().ordinal() != 5);
            else
                do {
                    dices[i].rollDice();
                } while(dices[i].getDiceNumber().ordinal() != 4);
        }
        return dices;
    }

    @Test
    void isValidGuessTest(){
        Dice[] dices = SetupDiceArray();
        boolean actual = isValidGuess(dices);
        boolean expected = false;
        assertEquals(expected, actual);
    }
    @Test
    void hasNoDuplicatesTest(){
        Dice[] dices = SetupDiceArray();
        dices[5].putAside();
        boolean actual = hasNoDuplicates(dices, diceNumber.SIX);
        boolean expected = false;
        assertEquals(expected, actual);
    }

}