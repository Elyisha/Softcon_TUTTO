package Cards;
import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Gameflow.TurnResult;
import Input.DecideDice;
import java.util.ArrayList;
import java.util.Collections;

public class FireworksCard extends Card implements CardInterface {
    public FireworksCard() {super(CardsValue.FIREWORK);} //inherits Constructor from abstract class

    public TurnResult getRoll() {
        short currentPoints = 0;
        ArrayList<Dice> dices = new ArrayList<>(); //stores the dices
        for (byte i = 0; i < 6; i++) dices.add(new Dice()); //instantiate the dices

        do {
            ArrayList<Dice> countDices = new ArrayList<>();
            rollDisplayCount(dices, countDices);

            //now check if roll was at least possibly valid, if not, break the while loop.
            if (ValidDice.countPoints(countDices) == 0) {
                Display.rolledaNull();
                break;
            }

            //now ask user which ones to put aside and put them aside
            currentPoints += DecideDice.decideDice(Collections.unmodifiableList(dices), true);
            //now check & reset if FireWork
            FireWorkChecker(dices);

        } while (true);

        return new TurnResult(currentPoints, false);

    }
    private static void FireWorkChecker(ArrayList<Dice> dices) { //see how many have been put aside (for FIREWORK recognization)
        byte howManyAside = 0;
        for (byte i = 0; i < 6; i++) {
            if (dices.get(i).isAside()) howManyAside++;
        }
        //if all have been put aside, reset and continue with loop

        if (howManyAside == 6) {
            dices.clear();
            for (byte i = 0; i < 6; ++i) {
                dices.add(new Dice());
            }
        }
    }
}
