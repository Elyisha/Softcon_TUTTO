package Turn;

import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Gameflow.Input;

public class FireworksTurn extends AbstractTurn{

    public TurnResult fireworksTurn(short currentPoints) {
        short newPoints = currentPoints;
        boolean nullThrow = false;
        while (!nullThrow) {
            TurnResult result = getRoll();
            newPoints += result.points;

            //TODO UPDATE WHILE VARIABLE
            //TODO CORRECTLY RETURN EEVERY CORRECT VALUE - SEE ASSIGNMENT DESCRIPTION

        }
        if (result.b) newPoints += newPoints; //double it if tutto
        return new TurnResult(newPoints, result.b);
    }

    public TurnResult getFireWorkRoll() {
        Dice[] dices = new Dice[6]; //stores the dices
        boolean tutto = false;
        short currentPoints = 0;

        //instantiate the dices (doesn't roll them for the first time!)
        for (byte i = 0; i < 6; i++) {
            dices[i] = new Dice();
        }

        boolean roll = true;
        while (roll) {
            byte howManyAside = 0;
            for (byte i = 0; i < 6; i++) {
                if (!dices[i].isAside()) { //if it was not put aside yet...
                    dices[i].rollDice(); //...roll it...
                    Display.displayDice(dices[i].getDiceNumber(), i); //...print it
                }
            } //ends print dices for-loop

            //now: check if roll was at least possibly valid, if not, break the while loop, else add points
            int countPointsCache = ValidDice.countPoints(dices);
            if (countPointsCache == 0) break; //CAN BE OVERGIVEN AS DICES THEMSELVES ARE IMMUTABLE.?
            //else currentPoints += countPointsCache;
            //TODO: this must only check not-put-aside dices!!

            //now ask user which ones to put aside and put them aside
            Input.decideDice(dices); //muss ich das jetzt nochmals kopieren oder wurde eigentlich nur das bereits bestehende Objekt verändert?

            //see how many have been put aside
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
