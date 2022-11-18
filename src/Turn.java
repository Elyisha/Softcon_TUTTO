import Cards.*;
import Dices.*;
public class Turn { // Singleton?
//todo: read only wrapper for the arrays when i give them to isvalid etc
private Dice[] dices;
    public short turn() {
        boolean roll = true;
        final CardsValue card = Deck.getCard(); //pull the card. final / private ?

        Dice[] dices = new Dice[5]; //stores the dices
        for (byte i = 0; i < 5; i++) { //instantiate the dices (rolls them for the first time)
            dices[i] = new Dice();
        }
        byte putAsideCounter = 0; //what was this again for?


        while (roll) {
            Display.DisplayCard(card); //show user the card
            for (byte i = 0; i < 5; i++) {
                if (!dices[i].isAside()) { //if it was not put aside yet...
                    //maybe roll it here again??
                    Display.displayDice(dices[i].getDiceNumber()); //...print it
                }
            } //ends print dices for-loop

            //now: check if roll was valid, if not, break the while loop
            if (ValidDice.countPoints(copyDiceEnums()) == 0) break;

            //now ask user which ones to put aside and put them aside
            boolean[] deciderArray = Input.decideDice(); //copy it??
            for (byte i = 0; i < 5; i++) {
                if (deciderArray[i]) {
                    dices[i].putAside();
                    putAsideCounter++;
                }
            }

            //ask user if he wants to end or roll again, then sets while-loop variable accordingly
            roll = Input.askUserRE();

        }

        //count & return points
        return countPoints(copyDiceEnums(), card);
    }


    private diceNumber[] copyDiceEnums(){
        diceNumber[] numArray = [5];
        for (byte i = 0; i < 5; i++) {
            numArray[i] = this.dices[i].getDiceNumber(); //store it into a copy array to respect encapsulation
        }
        return numArray;
    }
}

/*
        for (byte i = 0; i < 6; i++) { //copy the data for the validator
            numbersOfCurrentRoll[i] = dices[i].getDiceNumber(); //store it into a copy array to respect encapsulation and prepare it for the isValid function
        }
* */
