/*package Turn;

import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Input.Input;

import java.util.ArrayList;

public class StraightTurn extends AbstractTurn{

public static TurnResult getRoll() {
    short currentPoints = 0;
    boolean tutto = false;
    Dice[] dices = new Dice[6]; //stores the dices
    for (byte i = 0; i < 6; i++) {dices[i] = new Dice();} //instantiate the dices (doesn't roll them!)

    do {
        ArrayList<Dice> countDices = new ArrayList<>();
        rollDisplayCount(dices, countDices);
        //now check if roll was at least possibly valid, if not, break the while loop, else add points
        if (!ValidDice.hasValidDicesLeft(countDices)) { //todo needs ArrayList implementation - done but needs testing
            Display.rolledaNull();
            break;
        }
        //now ask user which ones to put aside and put them aside
        Input.straightDecideDice(dices); //todo hier übergebe ich reference, ist das okay? prob not...
        //now check & break if tutto
        tutto = tuttoChecker(dices);
        if (tutto) {
            currentPoints = 2000;
            break;
        }

    } while (true);

    return new TurnResult(currentPoints, tutto);
}

protected static boolean tuttoChecker(Dice[] dices) { //see how many have been put aside (for tutto recognization)
    byte howManyAside = 0;
    for (byte i = 0; i < 6; i++) {
        if (dices[i].isAside()) howManyAside++;
    }
    //if all have been put aside, return true
    return howManyAside == 6;
}



public static TurnResult getRoll() {
    byte howManyAside = 0;
    Dice[] dices = new Dice[6]; //stores the dices
    boolean tutto = false;
    short currentPoints=0;

    //instantiate the dices (doesn't roll them for the first time!)
    for (byte i = 0; i < 6; i++) {
        dices[i] = new Dice();
    }
    boolean roll = true;
    while (roll) {
        Dice[] countDices = new Dice[6 - howManyAside];
        howManyAside = 0;
        byte forLoopCounter=0; //TODO really ugly
        for (byte i = 0; i < 6; i++) {
            if (!dices[i].isAside()) { //if it was not put aside yet...
                dices[i].rollDice(); //...roll it...
                Display.displayDice(dices[i].getDiceNumber(), (byte) (i+1)); //...print it
                countDices[forLoopCounter++] = dices[i]; //TODO kinda ugly
            }
        } //ends print dices for-loop

        //now: check if roll was at least possibly valid, if not, break the while loop, else add points
        if (!ValidDice.hasValidDicesLeft(dices)) {//DONE: should only count if it is valid (those NOT put aside yet)
            Display.rolledaNull();
            currentPoints = 0;
            roll = false;
        }

        //now ask user which ones to put aside and put them aside
        Input.straightDecideDice(dices); //muss ich das jetzt nochmals kopieren oder wurde eigentlich nur das bereits bestehende Objekt verändert?
        //TODO: ^^ muss verändert werden durch neue decideDice

        //see how many have been put aside (for tutto/straight recognization)
        for (byte i = 0; i < 6; i++) {
            if (dices[i].isAside()) {
                howManyAside++;
            }
        }
        //if all have been put aside, end while loop and set tutto to true
        if (howManyAside == 6) {
            tutto = true;
            currentPoints = 2000;
            roll = false;
        }


    }
    return new TurnResult(currentPoints, tutto);
}


}*/
