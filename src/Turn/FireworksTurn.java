package Turn;
import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Gameflow.Input;
import java.util.ArrayList;

public class FireworksTurn extends AbstractTurn{
    public static TurnResult fireworksTurn() {
        byte howManyAside;
        short roundPoints = 0;

        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < 6; i++) {dices[i] = new Dice();} //instantiate the dices (doesn't roll them for the first time!)


        while (true) {
            ArrayList<Dice> countDices = new ArrayList<>();
            howManyAside = 0;
            for (byte i = 0; i < 6; i++) {
                if (!dices[i].isAside()) { //if it was not put aside yet...
                    dices[i].rollDice(); //...roll it...
                    Display.displayDice(dices[i].getDiceNumber(), (byte) (i+1)); //...print it...
                    countDices.add(dices[i]); //...put those aside that are still in the game to check their validity
                }
            } //ends print dices for-loop


            //now: check if roll was at least possibly valid, if not, break the while loop.
            if (ValidDice.countPoints(countDices) == 0) {
                Display.rolledaNull();
                break;
            }
            //now ask user which ones to put aside and put them aside
            roundPoints += Input.decideDice(dices); //muss ich das jetzt nochmals kopieren oder wurde eigentlich nur das bereits bestehende Objekt verändert?

            //see how many have been put aside (for tutto recognization)
            for (byte i = 0; i < 6; i++) {
                if (dices[i].isAside()) {
                    howManyAside++;
                }
            }
            //if all have been put aside, end while loop and reset all dices to go to the next round...
            if (howManyAside == 6) {
                for (byte i = 0; i < 6; i++) {
                    dices[i] = new Dice();
                }

            }
        }
        return new TurnResult(roundPoints, false); //boolean tutto is unnecessary

    }

}
