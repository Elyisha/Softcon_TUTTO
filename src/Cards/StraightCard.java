package Cards;
import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Input.Input;
import Gameflow.TurnResult;
import Input.DecideDice;
import java.util.ArrayList;

public class StraightCard extends Card implements CardInterface {
    public StraightCard() {super(CardsValue.STRAIGHT);} //inherits Constructor from abstract class

    public TurnResult getRoll() {
        short currentPoints = 0;
        boolean tutto = false;
        ArrayList<Dice> dices = new ArrayList<>(); //stores the dices
        for (byte i = 0; i < 6; i++) dices.add(new Dice()); //instantiate the dices (doesn't roll them!)

        do {
            ArrayList<Dice> countDices = new ArrayList<>();
            rollDisplayCount(dices, countDices);
            //now check if roll was at least possibly valid, if not, break the while loop, else add points
            if (!ValidDice.hasValidDicesLeft(dices)) { //todo needs ArrayList implementation - done but doesn't work right!! doesn't trigger
                Display.rolledaNull();
                break;
            }
            //now ask user which ones to put aside and put them aside
            DecideDice.straightDecideDice(dices); //todo hier übergebe ich reference, ist das okay? prob not...
            //now check & break if tutto
            tutto = StraightTuttoChecker(dices);
            if (tutto) {
                currentPoints = 2000;
                break;
            }

        } while (true);

        return new TurnResult(currentPoints, tutto);
    }
    private boolean StraightTuttoChecker(ArrayList<Dice> dices) { //see how many have been put aside (for tutto recognization)
        byte howManyAside = 0;
        for (byte i = 0; i < 6; i++) {
            if (dices.get(i).isAside()) howManyAside++;
        }
        //if all have been put aside, return true
        return howManyAside == 6;
    }

}
