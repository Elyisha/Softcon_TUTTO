package Input;
import java.util.Scanner;


public class Input {

    //takes The names of the players participating
    public static String[] takeNames(){
        String[] Names;

        //Initializes scanner and prints starting message
        Scanner NameInput = new Scanner(System.in);
        System.out.println("Put in your names seperated by a comma (example: Hans,Peter,Sara)");

        //takes input and validates it, if input is in correct format it will end the while loop
        while(true) {
            String Input = NameInput.nextLine().replaceAll("\\s+", "");
            Names = Input.split(",");

            //validates and returns input
            if(Input.length() == 0) System.out.println("Must have at least 2 Players!");
            else if(Input.matches(".*[0-9].*")) System.out.println("Names can't contain numbers!");
            else if(Names.length > 4) System.out.println("Maximum 4 Players are allowed!");
            else if(Names.length < 2) System.out.println("Minimum 2 players are required!");
            else {
                return Input.split(",");
            }
        }
    }

    //takes the maximum points of the game from user
    public static Short takeMaxPoints(){

        //Initializes scanner and prints starting message
        Scanner PointInput = new Scanner(System.in);
        System.out.println("To how many points do you want to play? (range from 500 to 32000 points)");

        //takes input and validates it, if input is in the correct range it will end the while loop
        while(true) {
            String Input = PointInput.nextLine().replaceAll("\\s+", "");

            //validates and returns input
            if(Input.length() == 0) System.out.println("Must type in an amount!");
            else if(!Input.matches("[0-9]+")) System.out.println("Can only type-in numbers!");
            else if(Integer.parseInt(Input) < 500 || Integer.parseInt(Input) > 32000)
                System.out.println("Allowed point range is from 500 to 32000");
            else return Short.valueOf(Input);
        }
    }

    //asks user whether he wants to Display (D) or Roll (R)
    //POST: returns true if user wants to Roll (R) and false if he wants to Display (D)
    public static boolean askUserDR(){

        //Initializes scanner and prints starting message
        Scanner DRInput = new Scanner(System.in);
        System.out.println("Do you want to Game.Display (D) or Roll (R)?");

        //takes and validates input
        while(true){
            String Input = DRInput.nextLine().replaceAll("\\s","").toUpperCase();

            //Validates and returns input
            if(inputValidation(Input, 'D') == 'R') return true;
            if(inputValidation(Input, 'D') == 'D') return false;
        }
    }

    //asks user whether he wants to End (E) or Roll (R)
    //POST: returns true if user wants to Roll (R) and false if he wants to End (E)
    public static boolean askUserRE(){

        //Initializes scanner and prints starting message
        Scanner REInput = new Scanner(System.in);
        System.out.println("Do you want to Roll (R) or End (E)?");

        //takes and validates input
        while(true){
            String Input = REInput.nextLine().replaceAll("\\s","").toUpperCase();

            //Validates and returns input
            if(inputValidation(Input, 'E') == 'R') return true;
            if(inputValidation(Input, 'E') == 'E') return false;
        }
    }
    //Checks that the Input string only contains either an 'R' or a second char
    private static char inputValidation(String Input, char second) {

        //Validates String and returns the result as char
        if(Input.length() != 1) System.out.println("Must type in one character only!");
        else if(Input.charAt(0) == 'R') return 'R';
        else if(Input.charAt(0) == second) return second;
        else System.out.println("Must type in R or E");

        return 0;
    }
}



