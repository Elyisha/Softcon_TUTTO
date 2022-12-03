package Dices;
import java.util.Random;

public class Dice {
    private final diceNumber diceNumber;
    private boolean putAside;
    public Dice() { // Konstruktor
        diceNumber = rndDiceNumber();
        putAside = false;
    }
    private diceNumber rndDiceNumber(){
        Random rand = new Random();
        return Dices.diceNumber.values()[rand.nextInt(0, 6)]; //return diceNumber > values > [random between 0 and 6]
    }
    public Dices.diceNumber getDiceNumber() {return diceNumber;}
    public void putAside() {
        this.putAside = true;
    }
    public boolean isAside() {return putAside;}
}
