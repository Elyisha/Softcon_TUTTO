package Dices;
import java.util.Random;

public class Dice {
    Random rand = new Random(); //so it doesn't get invoked every time, saves space
    private diceNumber diceNumber;
    boolean putAside; //hier schon instanzieren oder erst im Konstruktor?
    public Dice() { // Konstruktor
        diceNumber = null; //don't let it be a new roll, this enforces it to roll them once
        putAside = false;
    }


    private diceNumber rndDiceNumber(){
        return Dices.diceNumber.values()[rand.nextInt(0, 6)]; //return diceNumber > values > [random between 0 and 6]
    }

    public void rollDice() {
        this.diceNumber = rndDiceNumber();
    }

    public Dices.diceNumber getDiceNumber() {
        diceNumber dicenrcopy = diceNumber; //THIS IS NOT A ENCAPSULATION-SAFE COPY - HOW??
        return dicenrcopy;
    }

    public void putAside() {
        this.putAside = true;
    }

    public boolean isAside() {
        if (putAside) { //return putAside ginge auch, ist aber unsafe wg encapsulation?? oder kann man?
            return true;
        }
        else {
            return false;
        }
    }
}
