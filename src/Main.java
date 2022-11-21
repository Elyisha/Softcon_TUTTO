import Dices.*;
import Game.Display;

public class Main {


    public static void main(String[] args) {
        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < dices.length; i++) { //instantiate the dices (rolls them for the first time)
            dices[i] = new Dice();
            dices[i].rollDice();
        }
        //System.out.println(dices[2].getDiceNumber().name() == "FOUR");
        for(int i = 0; i<dices.length;++i){
            Display.displayDice(dices[i].getDiceNumber());
        }
        System.out.println(ValidDice.countPoints(dices));
        /*
        dices[2].putAside();
        Game.Input.decideDice(dices);
*/




    }
}
