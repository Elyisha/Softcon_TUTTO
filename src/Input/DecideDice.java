package Input;

import Dices.Dice;
import Dices.ValidDice;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DecideDice {

    //asks the user which dices he wants to put aside and validates the user's choice
    public static short decideDice(List<Dice> dices, boolean fireworks) {

        //Initializes scanner and prints starting message
        Scanner DDInput = new Scanner(System.in);
        System.out.println("Which dices do you want to put aside? Enter your decision by separating " +
                "the dice-index with a comma (e.g. 2,4,5).");

        //takes and validates input, while loop will end as soon as the user gives a valid input
        while(true) {

            //creates list with the indices of the dice that the user wants to put aside and converts the
            //string input to char-array
            List<Integer> numbers;
            String sInput = DDInput.nextLine().replaceAll("\\s","");
            char[] Input = sInput.toCharArray();

            //Validates the users choice of dices
            numbers = InputValidation(Input, dices, false);
            if(numbers == null) continue;

            //creates dice-array with the user's choice of dices
            ArrayList<Dice> countdices = new ArrayList<Dice>();
            for (int i = 0; i < numbers.size();++i) countdices.add(dices.get(numbers.get(i)-1));

            //Check whether there are single dices that don't give any points (additional input-validation)
            if(!ValidDice.isValidGuess(countdices)) {
                System.out.println("Must only take out dices that give points");
                continue;
            }

            //Counts the points of the dices the user wants to put aside and returns it
            //if the current card is fireworks, then the dices won't actually be put aside
            //but their points will be counted
            ArrayList<Dice> countDices = new ArrayList<>(numbers.size());
            for (byte i = 0; i < numbers.size(); i++) {
                if(!fireworks) dices.get(numbers.get(i)-1).putAside();
                countDices.add(i, dices.get(numbers.get(i) - 1)); //add to countDices
            }

            //If the fireworks card is active there is an additional check that makes sure the user puts aside all
            //possible points that can be achieved in the current roll
            if(fireworks){
                ArrayList<Dice> notAsideDices = new ArrayList<>();
                for(Dice dice: dices) if(!dice.isAside()) notAsideDices.add(dice);
                if(ValidDice.countPoints(notAsideDices) != ValidDice.countPoints(countDices)) {
                    System.out.println("You have to keep the highest amount of points possible to achieve in this round. Try again.");
                    continue;
                }
                else {
                    for(Dice dice: countDices) dice.putAside();
                }
            }

            //prints achieved points and returns them
            if (!fireworks) System.out.println("You put " + Dices.ValidDice.countPoints(countDices) + " aside.");
            return Dices.ValidDice.countPoints(countDices);
        }
    }

    //asks the user which dices he wants to put aside if the card is "straight" and validates the user's choice
    public static void straightDecideDice(List<Dice> dices) {

        //Initializes scanner and prints starting message
        Scanner sDDInput = new Scanner(System.in);
        System.out.println("Which dices do you want to put aside? Enter your decision by separating " +
                "the dice-index with a comma (e.g. 2,4,5).");

        //Takes input and validates it
        while(true) {
            List<Integer> numbers;
            String sInput = sDDInput.nextLine().replaceAll("\\s","");
            char[] Input = sInput.toCharArray();

            //Input validation
            numbers = InputValidation(Input, (List<Dice>) dices, true);

            //If Input is valid it'll put aside the chosen dices
            if(numbers != null) {
                for (Integer number : numbers) dices.get(number - 1).putAside();
                return;
            }
        }
    }

    //Validates the user input according to the game-rules and return a list with indices of the dices that need
    //to be put aside
    private static List<Integer> InputValidation(char[] Input, List<Dice> dices, boolean straight) {

        //This list will store the indices of which dices the user wants to put aside
        List<Integer> numbers = new ArrayList<>();

        //Input cannot be of length 0
        if(Input.length == 0) {
            System.out.println("Must type in at least 1 dice");
            return null;
        }

        //helper variable (counts the amount of iterations)
        int i = 0;

        //for-loop validates every dice the user wants to put aside
        for(char Char: Input) {
            int currentCharToInt;

            if(i % 2 == 0) {
                //checks whether current char is an int and if so stores it as int
                if(validNumber(Char)) currentCharToInt = Character.getNumericValue(Char-1);
                else {
                    System.out.println("Must type in the numbers according to the correct format (e.g. " +
                            "1,3,4)");
                    return null;
                }
                //Checks whether dice is already aside
                if (alreadyAside(dices, currentCharToInt)) {
                    System.out.println("Dice at position " + Char + " has already been put aside!");
                    return null;
                }
                //if the card is straight this statement will check for duplicate dices in the current input
                else if(straight && !hasDiceDuplicate(numbers, dices, currentCharToInt)) {
                    System.out.println("Cannot put aside 2 dices with the same dice-values (dice " + Char + " has already been put aside)");
                    return null;
                }
                //if the card is straight this statement will check that no dice with the current dice-value has been put aside yet
                else if(straight && !ValidDice.hasNoDuplicates(dices, dices.get(currentCharToInt).getDiceNumber())) {
                    System.out.println("you have previously put aside a dice with the dice-value " + (dices.get(currentCharToInt).getDiceNumber().ordinal()+1));
                    return null;
                }
                //if input is valid die it adds its index to the list of indices
                else numbers.add(Character.getNumericValue(Char));
            }

            //char after a number must be a comma at all times
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


    //Checks whether the user is trying to put 2 dices with the same value aside (not allowed in straight)
    private static boolean hasDiceDuplicate(List<Integer> numbers, List<Dice> dices, int i) {
        for (Integer number : numbers) {
            if (dices.get(i).getDiceNumber() == dices.get(number - 1).getDiceNumber()) return false;
        }
        return true;
    }

    //Checks whether the char (part of Input) is a number in the allowed range
    private static boolean validNumber(char i) {

        return (Character.isDigit(i) && (Character.getNumericValue(i) < 7) && (Character.getNumericValue(i) > 0));
    }

    //Checks whether a die has already been put aside previously
    private static boolean alreadyAside(List<Dice> dices, int i) {return dices.get(i).isAside();}

}
