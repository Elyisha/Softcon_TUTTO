package Dices;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidDice {

    //Checks if there are any dices in the dice array that do not give any points and are
    //therefore invalid
    //POST: returns true if there are no invalid dices and false if there are any
    public static boolean isValidGuess(Dice[] dices) {

        //converts dice-array to a list to get a value-array back with the values of every dice
        //e.g. if there are 3 dices with value 5 then the value array at index 4 will contain 3
        List<Dice> list = Arrays.asList(dices);
        int[] values = getValueArray(list, false);

        //for-loop does the checking
        for(byte i = 1; i < values.length;++i) {
            if(i == 4) continue;
            if(values[i] > 0 && values[i] != 3) {
                return false;
            }
        }
        return true;
    }

    //Checks whether there is a die put Aside that has the same value with the one being put Aside (only in Straight)
    //POST: Returns false if there is a die put Aside in the array with the same diceNumber as the parameter diceNum
    public static boolean hasNoDuplicates(Dice[] dices, diceNumber diceNum) {
        for (Dice dice : dices) {
            if (dice.isAside() && dice.getDiceNumber() == diceNum) return false;
        }
        return true;
    }

    //checks whether there is a valid dice left in the dice-array that can be put-Aside (only in Straight)
    public static boolean hasValidDicesLeft(ArrayList<Dice> dices){

        //gets value array with the values of the dices
        int[] values = getValueArray(dices, true);

        //does the checking
        for (Dice dice : dices) {
            if (!dice.isAside()) {
                if (values[dice.getDiceNumber().ordinal()] == 0) return true;
            }
        }
        return false;
    }

    //returns an array with the values of the dices in the dice-list, when use for straight there
    //is an additional condition
    private static int[] getValueArray(List<Dice> dices, boolean straight) {
        int[] values = new int[6];
        Arrays.fill(values, 0);

        //sorts out the values and puts them into the value array
        for (Dice dice : dices) {
            if(!straight)
                values[dice.getDiceNumber().ordinal()] += 1;
            else if(dice.isAside()) values[dice.getDiceNumber().ordinal()] += 1;
        }
        return values;
    }

    //counts the points the dices in the ArrayList give according to the game-rules
    public static short countPoints(ArrayList<Dice> dices) {

        //gets value-array and declares helper variables
        int[] values = getValueArray(dices, false);
        short points = 0;
        int triplet;
        boolean hasTriplet = true;

        //does the counting, first checks the value-array for triplets, if none have been found it will look
        //for single die that give points (fives and ones)
        while(true) {
            if (hasTriplet) {

                //checks for triplets
                triplet = findTriplet(values);

                //adds points if there have been found any triplets, else sets hasTriplet to false
                if(triplet == 0) hasTriplet = false;
                else if(triplet == 1) points += 1000;
                else points += triplet*100;
            } else {

                //checks for ones
                if(values[0] > 0) {
                    points += 100;
                    values[0] -= 1;
                }

                //checks for fives
                else if (values[4] > 0) {
                        points += 50;
                        values[4] -= 1;
                }
                else break;
            }
        }
        return points;
    }

    //finds a triplet in a value array
    private static int findTriplet(int[] values) {
        for (byte i = 0; i < values.length; ++i) {
            if (values[i] >= 3) {
                values[i] -= 3;
                return i + 1;
            }
        }
        return 0;
    }

    //todo: Function can be deleted
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
