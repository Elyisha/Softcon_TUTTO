import Dices.*;
public class Main {


    public static void main(String[] args) {
        Dice[] dices = new Dice[5]; //stores the dices
        for (byte i = 0; i < 5; i++) { //instantiate the dices (rolls them for the first time)
            dices[i] = new Dice();
        }
        Input.decideDice(dices);





    }
}