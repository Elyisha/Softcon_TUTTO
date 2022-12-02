package Cards;
import Dices.Dice;
import Dices.ValidDice;
import Gameflow.Display;
import Gameflow.TurnResult;
import Input.DecideDice;
import java.util.ArrayList;

public class FireworksCard extends Card implements CardInterface {
    public FireworksCard() {super(CardsValue.FIREWORK);} //inherits Constructor from abstract class

    public TurnResult getRoll() {
        short currentPoints = 0;
        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < 6; i++) {dices[i] = new Dice();} //instantiate the dices (doesn't roll them!)

        do {
            ArrayList<Dice> countDices = new ArrayList<>();
            rollDisplayCount(dices, countDices);
            //now check if roll was at least possibly valid, if not, break the while loop.
            if (ValidDice.countPoints(countDices) == 0) {
                Display.rolledaNull();
                break;
            }
            //now ask user which ones to put aside and put them aside

            short decidepoints;
            while(true) {
                decidepoints = DecideDice.decideDice(dices, true); //todo hier übergebe ich reference, ist das okay? prob not...
                if (decidepoints == ValidDice.countPoints(countDices)) break;
                else System.out.println("You have to keep the highest amount of points possible to achieve in this round. Try again.");
            }
            //now check & reset if FireWork
            FireWorkChecker(dices);

        } while (true);

        return new TurnResult(currentPoints, false);

    }
    private static void FireWorkChecker(Dice[] dices) { //see how many have been put aside (for FIREWORK recognization)
        byte howManyAside = 0;
        for (byte i = 0; i < 6; i++) {
            if (dices[i].isAside()) howManyAside++;
        }
        //if all have been put aside, reset and continue with loop

        if (howManyAside == 6) {
            for (byte i = 0; i < 6; i++) {
                dices[i] = new Dice();
            }
        }
    }
}
