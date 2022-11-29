package Dices;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidDice {


    public static boolean isValidGuess(Dice[] dices) {
        List<Dice> list = Arrays.asList(dices);
        int[] values = getValueArray(list, false);

        for(byte i = 1; i < values.length;++i) {
            if(i == 4) continue;
            if(values[i] > 0 && values[i] != 3) {
                return false;
            }
        }
        return true;

    }

    public static boolean hasNoDuplicates(Dice[] dices, diceNumber diceNum) {
        for (Dice dice : dices) {
            if (dice.isAside() && dice.getDiceNumber() == diceNum) return false;
        }
        return true;
    }

    public static boolean hasValidDicesLeft(ArrayList<Dice> dices){
        int[] values = getValueArray(dices, true);

        for (Dice dice : dices) {
            if (!dice.isAside()) {
                if (values[dice.getDiceNumber().ordinal()] == 0) return true;
            }
        }

        return false;
    }

    private static int[] getValueArray(List<Dice> dices, boolean straight) {
        int[] values = new int[6];
        Arrays.fill(values, 0);

        for (Dice dice : dices) {
            if(!straight)
                values[dice.getDiceNumber().ordinal()] += 1;
            else if(dice.isAside()) values[dice.getDiceNumber().ordinal()] += 1;
        }

        return values;
    }

    public static short countPoints(ArrayList<Dice> dices) {
        short points = 0;
        int triplet = 0;
        int[] values = getValueArray(dices, false);
        boolean hasTriplet = true;



        while(true) {
            if (hasTriplet) {
                triplet = findTriplet(values);
                if(triplet == 0) hasTriplet = false;
                else if(triplet == 1) {points += 1000; triplet = 0;}
                else {points += triplet*100; triplet = 0;}
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

    private static int findTriplet(int[] values) {
        for (byte i = 0; i < values.length; ++i) {
            if (values[i] >= 3) {
                values[i] -= 3;
                return i + 1;
            }
        }
        return 0;
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
}
