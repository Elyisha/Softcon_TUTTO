package Cards;
import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Gameflow.Input;
import Gameflow.TurnResult;

import java.util.ArrayList;

public class StraightCard extends Card implements CardInterface {
    public StraightCard() {super(CardsValue.STRAIGHT);} //inherits Constructor from abstract class

    public void controlTurn(){
        //todo
    }
    public TurnResult getRoll() { //todo nach controlTurn: das hier private machen
        short currentPoints = 0;
        boolean tutto = false;
        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < 6; i++) {dices[i] = new Dice();} //instantiate the dices (doesn't roll them!)

        do {
            ArrayList<Dice> countDices = new ArrayList<>();
            rollDisplayCount(dices, countDices);
            //now check if roll was at least possibly valid, if not, break the while loop, else add points
            if (!ValidDice.hasValidDicesLeft(countDices)) { //todo needs ArrayList implementation - done but doesn't work right!! doesn't trigger
                Display.rolledaNull();
                break;
            }
            //now ask user which ones to put aside and put them aside
            Input.straightDecideDice(dices); //todo hier übergebe ich reference, ist das okay? prob not...
            //now check & break if tutto
            tutto = StraighttuttoChecker(dices);
            if (tutto) {
                currentPoints = 2000;
                break;
            }

        } while (true);

        return new TurnResult(currentPoints, tutto);
    }
    private boolean StraighttuttoChecker(Dice[] dices) { //see how many have been put aside (for tutto recognization)
        byte howManyAside = 0;
        for (byte i = 0; i < 6; i++) {
            if (dices[i].isAside()) howManyAside++;
        }
        //if all have been put aside, return true
        return howManyAside == 6;
    }

}
