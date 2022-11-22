package Gameflow;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import Dices.*;

public class Input {

    public static String[] takeNames(){     //alphabetic order
        String[] Names;
        Scanner NameInput = new Scanner(System.in);

        System.out.println("Put in your names seperated by a coma (example: Hans, Peter, Sara)");

        while(true) {
            String Input = NameInput.nextLine().replaceAll("\\s+", "");

            if(Input.length() == 0) System.out.println("Must have at least 2 Players!");
            else if(Input.matches(".*[0-9].*")) System.out.println("Names can't contain numbers!");
            else {
                return Names = Input.split(",");
            }
        }
    }

    public static Short takeMaxPoints(){
        Short MaxPoints;
        Scanner PointInput = new Scanner(System.in);

        System.out.println("To how many points do you want to play?");

        while(true) {
            String Input = PointInput.nextLine().replaceAll("\\s+", "");

            if(Input.length() == 0) System.out.println("Must type in an amount!");
            else if(!Input.matches("[0-9]+")) System.out.println("Can only type-in numbers!");
            else if(Integer.valueOf(Input) < 500 || Integer.valueOf(Input) > 32000)
                System.out.println("Allowed point range is from 500 to 32000");
            else return MaxPoints = Short.valueOf(Input);
        }
    }
    public static boolean askUserDR(){     //change return type to boolean
        char DR;
        Scanner DRInput = new Scanner(System.in);

        System.out.println("Do you want to Game.Display (D) or Roll (R)?");

        while(true){
            String Input = DRInput.nextLine().replaceAll("\\s","").toUpperCase();

            if(Input.length() != 1) System.out.println("Must type in one character only!");
            else if(!(Input.contains("R") || Input.contains("D"))) System.out.println("Must type in D or R");
            else {
                DR = Input.charAt(0);
                return (DR == 'R');
            }
        }
    }
    public static boolean askUserRE(){     //change return type to boolean
        char RE;
        Scanner REInput = new Scanner(System.in);

        System.out.println("Do you want to Roll (R) or End (E)?");

        while(true){
            String Input = REInput.nextLine().replaceAll("\\s","").toUpperCase();

            if(Input.length() != 1) System.out.println("Must type in one character only!");
            else if(!(Input.contains("R") || Input.contains("E"))) System.out.println("Must type in R or E");
            else {
                RE = Input.charAt(0);
                return (RE == 'R');
            }
        }
    }

    public static short decideDice(Dice[] dices) {
        Scanner DDInput = new Scanner(System.in);
        System.out.println("Which dices do you want to put aside? Enter your decision by separating " +
                "the dice-index with a comma (e.g. 2,4,5).");

        while(true) {
            List<Integer> numbers = new ArrayList<Integer>();
            boolean asideCheck = true;
            boolean check = true;
            byte counter = 0;
            String Input = DDInput.nextLine().replaceAll("\\s","");

            for(int i = 0; i < Input.length(); ++ i) {
                if(i%2 == 0) {
                    if (!(Character.isDigit(Input.charAt(i)) && Character.getNumericValue(Input.charAt(i)) < dices.length+1
                    && Character.getNumericValue(Input.charAt(i)) > 0)) {
                        check = false;
                        break;
                    }
                    if(dices[Character.getNumericValue(Input.charAt(i)-1)].isAside()) {
                        System.out.println("Dice at position " + Character.getNumericValue(Input.charAt(i)) + " has already been put aside!");
                        asideCheck = false;
                        check = false;
                        break;
                    }
                    numbers.add(Character.getNumericValue(Input.charAt(i)));
                }
                else if(Input.charAt(i) != ',') {check = false; break;}
                }
            if(!asideCheck);
            else if(!check && asideCheck) {
                System.out.println("Must type in the numbers according to the correct format (e.g. " +
                        "1,3,4)");
            }
            else {
                Dice[] countDices = new Dice[numbers.size()];
                for (byte i = 0; i < numbers.size(); i++) {
                    dices[numbers.get(i)-1].putAside();
                    countDices[i] = dices[numbers.get(i)-1]; //add to countDices
                }

                return Dices.ValidDice.countPoints(countDices);
            }
        }
    }

}
