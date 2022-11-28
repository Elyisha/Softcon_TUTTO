import Dices.*;
import Gameflow.Display;
import Gameflow.Game;
import Gameflow.Input;
import Gameflow.Player;

public class Main {


    public static void main(String[] args) {

        Game G1 = Game.getInstance();
        G1.startGameFlow();




        /*
        Dice[] dices = new Dice[6]; //stores the dices
        for (byte i = 0; i < dices.length; i++) { //instantiate the dices (rolls them for the first time)
            dices[i] = new Dice();
            dices[i].rollDice();
            //dices[i].diceNumber = diceNumber.SIX;
        }



        dices[0].diceNumber = diceNumber.ONE;
        dices[1].diceNumber = diceNumber.FOUR;
        dices[2].diceNumber = diceNumber.FOUR;
        dices[3].diceNumber = diceNumber.FOUR;
        dices[4].diceNumber = diceNumber.FIVE;
        dices[5].diceNumber = diceNumber.SIX;

        while(true) Input.decideDice(dices);
        */
        /*
        dices[5].putAside();
        dices[5].diceNumber = diceNumber.FIVE;



        //System.out.println(dices[2].getDiceNumber().name() == "FOUR");
        for(int i = 0; i<dices.length;++i){
            Display.displayDice(dices[i].getDiceNumber(), (byte)(i+1));
        }

        dices[0].putAside();
        dices[1].putAside();
        dices[5].putAside();
        //dices[3].putAside();
        //dices[4].putAside();

        System.out.println(ValidDice.hasValidDicesLeft(dices));
*/
        /*
        System.out.println(ValidDice.countPoints(dices));

        dices[2].putAside();
        Gameflow.Input.decideDice(dices);

        Display.displayDice(diceNumber.FIVE, (byte) 1);
        Display.displayDice(diceNumber.TWO, (byte) 2);
        Display.displayDice(diceNumber.ONE, (byte) 3);
        Display.displayDice(diceNumber.THREE, (byte) 4);


        short a = 12;
        System.out.println(a);
    */

    }
}
