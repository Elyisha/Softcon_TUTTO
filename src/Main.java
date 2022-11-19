import Cards.CardsValue;
import Dices.*;
public class Main {


    public static void main(String[] args) {
        Dice[] dices = new Dice[1]; //stores the dices
        for (byte i = 0; i < dices.length; i++) { //instantiate the dices (rolls them for the first time)
            dices[i] = new Dice();
        }
        //System.out.println(dices[2].getDiceNumber().name() == "FOUR");
        for(int i = 0; i<dices.length;++i){
            System.out.println(dices[i].getDiceNumber().ordinal()+1);
        }
        System.out.println(ValidDice.countPoints(dices));
        /*
        dices[2].putAside();
        Input.decideDice(dices);
*/




    }
}
