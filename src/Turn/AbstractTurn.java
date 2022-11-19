package Turn;
import Cards.*;
import Dices.Dice;

abstract class AbstractTurn {

    public void getRoll(Deck d1) {
        Card card = d1.getCard();
        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < 6; i++) { //instantiate the dices (rolls them for the first time)
            dices[i] = new Dice();
        }

    }

}
