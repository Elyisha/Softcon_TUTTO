package Input;

import Dices.Dice;
import Dices.ValidDice;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DecideDice {
    public static short decideDice(Dice[] dices, boolean fireworks) {
        Scanner DDInput = new Scanner(System.in);
        System.out.println("Which dices do you want to put aside? Enter your decision by separating " +
                "the dice-index with a comma (e.g. 2,4,5).");


        while(true) {
            List<Integer> numbers;
            String sInput = DDInput.nextLine().replaceAll("\\s","");
            char[] Input = sInput.toCharArray();

            numbers = InputValidation(Input, dices, false);
            if(numbers == null) continue;

            //Check whether there are single dices that don't give any points
            Dice[] countdices = new Dice[numbers.size()];
            for (int i = 0; i < numbers.size();++i) countdices[i] = dices[numbers.get(i)-1];

            if(!ValidDice.isValidGuess(countdices)) {
                System.out.println("Must only take out dices that give points");
                continue;
            }

            //Count points and return
            ArrayList<Dice> countDices = new ArrayList<>(numbers.size());

            for (byte i = 0; i < numbers.size(); i++) {
                if(!fireworks) dices[numbers.get(i)-1].putAside();
                countDices.add(i, dices[numbers.get(i) - 1]); //add to countDices
            }
            if (!fireworks) System.out.println("You put " + Dices.ValidDice.countPoints(countDices) + " aside.");
            return Dices.ValidDice.countPoints(countDices);
        }
    }
    public static void straightDecideDice(Dice[] dices) {
        Scanner sDDInput = new Scanner(System.in);
        System.out.println("Which dices do you want to put aside? Enter your decision by separating " +
                "the dice-index with a comma (e.g. 2,4,5).");

        while(true) {
            List<Integer> numbers;
            String sInput = sDDInput.nextLine().replaceAll("\\s","");
            char[] Input = sInput.toCharArray();

            numbers = InputValidation(Input, dices, true);

            if(numbers != null) {
                for (Integer number : numbers) dices[number - 1].putAside();
                return;
            }
        }
    }
    private static List<Integer> InputValidation(char[] Input, Dice[] dices, boolean straight) {
        List<Integer> numbers = new ArrayList<>();

        if(Input.length == 0) {
            System.out.println("Must type in at least 1 dice");
            return null;
        }
        int i = 0;
        for(char Char: Input) {
            int currentCharToInt;
            if(i % 2 == 0) {
                if(validNumber(Char)) currentCharToInt = Character.getNumericValue(Char-1);
                else {
                    System.out.println("Must type in the numbers according to the correct format (e.g. " +
                            "1,3,4)");
                    return null;
                }
                if (alreadyAside(dices, currentCharToInt)) {
                    System.out.println("Dice at position " + Char + " has already been put aside!");
                    return null;
                }
                else if(straight && !hasDiceDuplicate(numbers, dices, currentCharToInt)) {
                    System.out.println("Cannot put aside 2 dices with the same dice-values (dice " + Char + " has already been put aside)");
                    return null;
                }
                else if(straight && !ValidDice.hasNoDuplicates(dices, dices[currentCharToInt].getDiceNumber())) {
                    System.out.println("you have previously put aside a dice with the dice-value " + (dices[currentCharToInt].getDiceNumber().ordinal()+1));
                    return null;
                }
                else numbers.add(Character.getNumericValue(Char));
            }
            else {
                if(Char != ',') {
                    System.out.println("Must type in the numbers according to the correct format (e.g. " +
                            "1,3,4)");
                    return null;
                }
            }
            ++i;
        }
        return numbers;
    }



    private static boolean hasDiceDuplicate(List<Integer> numbers, Dice[] dices, int i) {
        for (Integer number : numbers) {
            if (dices[i].getDiceNumber() == dices[number - 1].getDiceNumber()) return false;
        }
        return true;
    }
    private static boolean validNumber(char i) {

        return (Character.isDigit(i) && (Character.getNumericValue(i) < 7) && (Character.getNumericValue(i) > 0));
    }
    private static boolean alreadyAside(Dice[] dices, int i) {
        return dices[i].isAside();
    }

}
