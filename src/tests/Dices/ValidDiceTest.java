package Dices;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static Dices.ValidDice.*;
import static org.junit.jupiter.api.Assertions.*;

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
    public static ArrayList<Dice> convertToList(Dice[] dices) {
        ArrayList<Dice> arrayDices = new ArrayList<>();
        for(Dice dice: dices) {
            arrayDices.add(dice);
        }
        return arrayDices;
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
    @Test
    void getValueArrayTest(){
        Dice[] diceArray = SetupDiceArray();
        ArrayList<Dice> dices = convertToList(diceArray);
        boolean expected = true;
        boolean actual = hasValidDicesLeft(dices);
        assertEquals(expected,actual);
    }

    @Test
    void countPointsTest() {
        Dice[] diceArray = SetupDiceArray();
        ArrayList<Dice> dices = convertToList(diceArray);
        int expected = 500;
        int actual = countPoints(dices);
        assertEquals(expected, actual);
    }
}