package Input;
import java.util.Scanner;


public class Input {

    public static String[] takeNames(){     //alphabetic order

        Scanner NameInput = new Scanner(System.in);

        System.out.println("Put in your names seperated by a comma (example: Hans,Peter,Sara)");

        while(true) {
            String Input = NameInput.nextLine().replaceAll("\\s+", "");

            if(Input.length() == 0) System.out.println("Must have at least 2 Players!");
            else if(Input.matches(".*[0-9].*")) System.out.println("Names can't contain numbers!");
            else {
                return Input.split(",");
            }
        }
    }

    public static Short takeMaxPoints(){
        Scanner PointInput = new Scanner(System.in);

        System.out.println("To how many points do you want to play? (range from 500 to 32000 points)");

        while(true) {
            String Input = PointInput.nextLine().replaceAll("\\s+", "");

            if(Input.length() == 0) System.out.println("Must type in an amount!");
            else if(!Input.matches("[0-9]+")) System.out.println("Can only type-in numbers!");
            else if(Integer.parseInt(Input) < 500 || Integer.parseInt(Input) > 32000)
                System.out.println("Allowed point range is from 500 to 32000");
            else return Short.valueOf(Input);
        }
    }
    public static boolean askUserDR(){     //change return type to boolean
        Scanner DRInput = new Scanner(System.in);

        System.out.println("Do you want to Game.Display (D) or Roll (R)?");

        while(true){
            String Input = DRInput.nextLine().replaceAll("\\s","").toUpperCase();

            if(inputValidation(Input, 'D') == 'R') return true;
            if(inputValidation(Input, 'D') == 'E') return false;
        }

    }
    public static boolean askUserRE(){     //change return type to boolean
        Scanner REInput = new Scanner(System.in);

        System.out.println("Do you want to Roll (R) or End (E)?");

        while(true){
            String Input = REInput.nextLine().replaceAll("\\s","").toUpperCase();

            if(inputValidation(Input, 'E') == 'R') return true;
            if(inputValidation(Input, 'E') == 'E') return false;
        }
    }

    private static char inputValidation(String Input, char second) {
        if(Input.length() != 1) System.out.println("Must type in one character only!");

        if(Input.charAt(0) == 'R') return 'R';
        else if(Input.charAt(0) == second) return second;
        else System.out.println("Must type in R or E");

        return 0;
    }
}



