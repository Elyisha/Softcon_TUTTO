package Turn;
import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Gameflow.Input;
import java.util.ArrayList;

abstract class AbstractTurn {

    public static TurnResult getRoll() {
        boolean tutto = false;
        short currentPoints = 0;
        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < 6; i++) {dices[i] = new Dice();} //instantiate the dices (doesn't roll them for the first time!)

        boolean roll = true;
        while (roll) {

            ArrayList<Dice> countDices = new ArrayList<>();
            for (byte i = 0; i < 6; i++) {
                if (!dices[i].isAside()) { //if it was not put aside yet...
                    dices[i].rollDice(); //...roll it...
                    Display.displayDice(dices[i].getDiceNumber(), (byte) (i + 1)); //...print it...
                    countDices.add(dices[i]); //...put those aside that are still in the game to check their validity
                }
            }

            //now: check if roll was at least possibly valid, if not, break the while loop, else add points
            if (ValidDice.countPoints(countDices) == 0) {
                Display.rolledaNull();
                currentPoints = 0;
                break;
            }

            //now ask user which ones to put aside and put them aside
            currentPoints += Input.decideDice(dices, false); //todo hier übergebe ich reference, ist das okay? prob not...

            tutto = tuttoChecker(dices);
            if (tutto) break;

            //ask user if he wants to end or roll again, then sets while-loop variable accordingly
            roll = Input.askUserRE();

        }
        return new TurnResult(currentPoints, tutto);
    }

    public static boolean tuttoChecker(Dice[] dices) { //see how many have been put aside (for tutto recognization)
        byte howManyAside = 0;
        for (byte i = 0; i < 6; i++) {
            if (dices[i].isAside()) {
                howManyAside++;
            }
        }
        //if all have been put aside, return true
        return howManyAside == 6;
    }
}

