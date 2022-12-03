package Dices;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static Dices.ValidDice.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidDiceTest {

    public static ArrayList<Dice> SetupDiceArray() {
        ArrayList<Dice> dices = new ArrayList<>();
        for(int i = 0; i < 6; ++i) {
            dices.add(new Dice());
            if(i == 0)
                do {
                    dices.set(i, new Dice());
                } while(dices.get(i).getDiceNumber().ordinal() != 2);
            else if(i == 4)
                do {
                    dices.set(i, new Dice());
                } while(dices.get(i).getDiceNumber().ordinal() != 3);
            else if(i == 5)
                do {
                    dices.set(i, new Dice());
                } while(dices.get(i).getDiceNumber().ordinal() != 5);
            else
                do {
                    dices.set(i, new Dice());
                } while(dices.get(i).getDiceNumber().ordinal() != 4);
        }
        return dices;
    }

    @Test
    void isValidGuessTest(){
        ArrayList<Dice> dices = SetupDiceArray();
        boolean actual = isValidGuess(dices);
        boolean expected = false;
        assertEquals(expected, actual);
    }
    @Test
    void hasNoDuplicatesTest(){
        ArrayList<Dice> dices = SetupDiceArray();
        dices.get(5).putAside();
        boolean actual = hasNoDuplicates(dices, diceNumber.SIX);
        boolean expected = false;
        assertEquals(expected, actual);
    }
    @Test
    void hasValidDicesLeftTest(){
        ArrayList<Dice> diceArray = SetupDiceArray();
        boolean expected = true;
        boolean actual = hasValidDicesLeft(diceArray);
        assertEquals(expected,actual);
    }

    @Test
    void countPointsTest() {
        ArrayList<Dice> diceArray = SetupDiceArray();
        int expected = 500;
        int actual = countPoints(diceArray);
        assertEquals(expected, actual);
    }
}