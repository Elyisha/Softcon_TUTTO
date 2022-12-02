package Dices;
import java.util.Random;

public class Dice {
    private diceNumber diceNumber;
    private boolean putAside;
    public Dice() { // Konstruktor
        diceNumber = null; //don't let it be a new roll, this enforces it to roll them once
        putAside = false;
    }
    private diceNumber rndDiceNumber(){
        Random rand = new Random();
        return Dices.diceNumber.values()[rand.nextInt(0, 6)]; //return diceNumber > values > [random between 0 and 6]
    }
    public void rollDice() {
        this.diceNumber = rndDiceNumber();
    }
    public Dices.diceNumber getDiceNumber() {return diceNumber;}
    public void putAside() {
        this.putAside = true;
    }
    public boolean isAside() {return putAside;}
}
