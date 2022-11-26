package Turn;
import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Gameflow.Input;
import java.util.ArrayList;

abstract class AbstractTurn {

    public static TurnResult getRoll() {
        byte howManyAside;
        boolean tutto = false;
        short currentPoints = 0;
        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < 6; i++) {dices[i] = new Dice();} //instantiate the dices (doesn't roll them for the first time!)

        boolean roll = true;
        while (roll) {

            ArrayList<Dice> countDices = new ArrayList<>();
            howManyAside = 0;

            //now: check if roll was at least possibly valid, if not, break the while loop, else add points
            if (ValidDice.countPoints(countDices) == 0) {
                Display.rolledaNull();
                currentPoints = 0;
                break;
            }

            //now ask user which ones to put aside and put them aside
            currentPoints += Input.decideDice(dices); //todo hier übergebe ich reference, ist das okay? prob not...

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
