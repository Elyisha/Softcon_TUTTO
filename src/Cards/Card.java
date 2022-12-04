package Cards;

import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Input.Input;
import Gameflow.TurnResult;
import Input.DecideDice;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Card implements CardInterface {
    public final CardsValue aCardsValue;
    public Card(CardsValue cardsValue) {
        aCardsValue = cardsValue;
    }
    public CardsValue getValue(){
        return aCardsValue;
    }
    protected static TurnResult getAbstractRoll(boolean... alwaysrepeat) {
        short currentPoints = 0;
        boolean tutto = false;
        ArrayList<Dice> dices = new ArrayList<>(); //stores the dices
        for (byte i = 0; i < 6; i++) {dices.add(new Dice());} //instantiate the dices
        //if alwaysrepeat is not set: shouldask is true, and it asks. if alwaysrepeat == true: shouldask is false and it always repeats
        boolean shouldask = (alwaysrepeat.length >= 1) ? !alwaysrepeat[0] : true; //ignore IntelliJ warning
        boolean roll = true;
        do {
            ArrayList<Dice> countDices = new ArrayList<>();
            rollDisplayCount(dices, countDices);

            //now check if roll was at least possibly valid, if not, break the while loop, else add points
            if (ValidDice.countPoints(countDices) == 0) {
                Display.rolledaNull();
                currentPoints = 0;
                break;
            }
            //now ask user which ones to put aside and put them aside
            currentPoints += DecideDice.decideDice(Collections.unmodifiableList(dices), false);
            Display.showCurrentPointsAside(currentPoints);
            //now check & break if tutto
            tutto = tuttoChecker(dices);
            if (tutto) break;
            if (shouldask) roll = Input.askUserRE();
        } while (roll);

        return new TurnResult(currentPoints, tutto);
    }

    protected static void rollDisplayCount(ArrayList<Dice> dices, ArrayList<Dice> countDices){
        for (byte i = 0; i < 6; i++) {
            if (!dices.get(i).isAside()) { //if it was not put aside yet...
                dices.set(i, new Dice());//...roll it...
                Display.displayDice(dices.get(i).getDiceNumber(), (byte) (i + 1)); //...print it...
                countDices.add(dices.get(i)); //...put those aside that are still in the game to check their validity
            }
        }
    }

    protected static boolean tuttoChecker(ArrayList<Dice> dices) { //see how many have been put aside (for tutto recognization)
        byte howManyAside = 0;
        for (byte i = 0; i < 6; i++) {
            if (dices.get(i).isAside()) howManyAside++;
        }
        //if all have been put aside, return true
        return howManyAside == 6;
    }

}



