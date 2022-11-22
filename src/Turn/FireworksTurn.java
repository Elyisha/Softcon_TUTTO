package Turn;

import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Gameflow.Input;

public class FireworksTurn extends AbstractTurn{

    public TurnResult fireworksTurn() {

        byte howManyAside = 0;
        Dice[] dices = new Dice[6]; //stores the dices
        short roundPoints = 0;

        //instantiate the dices (doesn't roll them for the first time!)
        for (byte i = 0; i < 6; i++) {
            dices[i] = new Dice();
        }

        while (true) {
            Dice[] countDices = new Dice[6 - howManyAside];
            howManyAside = 0;
            byte forLoopCounter=0; //TODO really ugly
            for (byte i = 0; i < 6; i++) {
                if (!dices[i].isAside()) { //if it was not put aside yet...
                    dices[i].rollDice(); //...roll it...
                    Display.displayDice(dices[i].getDiceNumber(), i); //...print it
                    forLoopCounter++; //TODO really ugly //add indice for new countDices array
                    countDices[forLoopCounter] = dices[i]; //TODO kinda ugly
                }
            } //ends print dices for-loop

            //now: check if roll was at least possibly valid, if not, break the while loop.
            if (ValidDice.countPoints(countDices) == 0) {
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
