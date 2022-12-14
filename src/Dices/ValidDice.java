package Dices;
import Cards.*;


import java.util.ArrayList;
import java.util.Arrays;
public class ValidDice {


    public static boolean isValidGuess(Dice[] dices) {
        int[] values = new int[6];
        Arrays.fill(values, 0);

        for (Dice dice : dices) {
            values[dice.getDiceNumber().ordinal()] += 1;
        }

        for(byte i = 1; i < values.length;++i) {
            if(i == 4) continue;
            if(values[i] > 0 && values[i] != 3) {
                return false;
            }
        }
        return true;

    }

    public static short countPoints(ArrayList<Dice> dices) {
        short points = 0;
        short triplet = 0;
        int[] values = new int[6];
        Arrays.fill(values, 0);

        boolean hasTriplet = true;


        for (Dice dice : dices) {
            values[dice.getDiceNumber().ordinal()] += 1;
        }
        while(true) {
            if (hasTriplet) {
                for (byte i = 0; i < values.length; ++i) {

                    if (values[i] >= 3) {
                        triplet = (short) (i + 1);
                        values[i] -= 3;
                        break;
                    }
                }
                if(triplet == 1) {points += 1000; triplet = 0;}
                else if(triplet != 0) {points += triplet*100; triplet = 0;}
                else hasTriplet = false;
            } else {
                if(values[0] > 0) {
                    points += 100;
                    values[0] -= 1;
                }
                else if (values[4] > 0) {
                        points += 50;
                        values[4] -= 1;
                }
                else break;
            }
        }
        
    return points;

    }

    //Function can be deleted
    public static boolean hasValidDices(Dice[] dices){
        for (byte i = 0; i < dices.length; ++i){
            if(dices[i].isAside()) {
                for (int j = i + 1; j < dices.length; ++j) {
                    if ((dices[i].getDiceNumber().ordinal() == dices[j].getDiceNumber().ordinal()) && dices[j].isAside()) return false;
                }
            }
        }
        return true;
    }
    public static boolean hasNoDuplicates(Dice[] dices, diceNumber diceNum) {
        for (byte i = 0; i < dices.length; ++i) {
            if (dices[i].isAside() && dices[i].getDiceNumber() == diceNum) return false;
        }
        return true;
    }

    public static boolean hasValidDicesLeft(Dice[] dices){
        int[] values = new int[6];
        Arrays.fill(values, 0);

        for (Dice dice : dices) {
            if (dice.isAside()) values[dice.getDiceNumber().ordinal()] += 1;
        }
        for (Dice dice : dices) {
            if (!dice.isAside()) {
                if (values[dice.getDiceNumber().ordinal()] == 0) return true;
            }
        }

        return false;
    }

}
