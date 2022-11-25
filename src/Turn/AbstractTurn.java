package Turn;
import Dices.*;
import Gameflow.*;
import java.util.ArrayList;

//todo helper funktion isTutto(dices): boolean
abstract class AbstractTurn {

    public static TurnResult getRoll() {
        byte howManyAside;
        Dice[] dices = new Dice[6]; //stores the dices
        boolean tutto = false;
        short currentPoints = 0;

        //instantiate the dices (doesn't roll them for the first time!)
        for (byte i = 0; i < 6; i++) {
            dices[i] = new Dice();
        }

        boolean roll = true;
        while (roll) {

            ArrayList<Dice> countDices = new ArrayList<>();
            howManyAside = 0;
            for (byte i = 0; i < 6; i++) {
                if (!dices[i].isAside()) { //if it was not put aside yet...
                    dices[i].rollDice(); //...roll it...
                    Display.displayDice(dices[i].getDiceNumber(), (byte) (i+1)); //...print it..
                    countDices.add(dices[i]); //...put those aside that are still in the game to check their validity
                }
            } //ends print dices for-loop

            //now: check if roll was at least possibly valid, if not, break the while loop, else add points
            if (ValidDice.countPoints(countDices) == 0) {//DONE: should only count if it is valid (those NOT put aside yet)
                Display.pointsOfRoundLost();
                currentPoints = 0;
                break;
            }

            //now ask user which ones to put aside and put them aside
            currentPoints += Input.decideDice(dices); //muss ich das jetzt nochmals kopieren oder wurde eigentlich nur das bereits bestehende Objekt verändert?

            //see how many have been put aside (for tutto recognization)
            for (byte i = 0; i < 6; i++) {
                if (dices[i].isAside()) {
                    howManyAside++;
                }
            }
            //if all have been put aside, end while loop and set tutto to true
            if (howManyAside == 6) {
                tutto = true;
                break;
            }
            //ask user if he wants to end or roll again, then sets while-loop variable accordingly
            roll = Input.askUserRE();

        }
        return new TurnResult(currentPoints, tutto);
    }

}
