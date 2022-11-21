package Dices;
import Cards.*;


import java.util.Arrays;

public class ValidDice {

    public static short countPoints(Dice[] dices) {
        short points = 0;
        short triplet = 0;
        int[] values = new int[6];
        Arrays.fill(values, 0);

        boolean hasTriplet = true;


        for (byte i = 0; i < dices.length; ++i) {
            values[dices[i].getDiceNumber().ordinal()] += 1;
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

    //is validStraight methode checkt ob putAside würfel auch legit weggenommen können werden (bei Straight)
    //validStraight2 checkt ob noch würfel hat zum wegnehmen (bei Straight)
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

    public boolean stillDices(){


        return false;
    }

}
