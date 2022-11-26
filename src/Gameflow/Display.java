package Gameflow;

import Cards.*;
import Dices.*;

public class Display {

    public static void displayDice(diceNumber d1, byte b) {
        //assert d1 != null;
        printUpper();
        for(int i = 1; i<4; i++){
            printLeftBox();
            printInner(d1, i);
            printRightBox();
        }
        printUnder(b);
    }

    public static void rolledaNull(){
        System.out.println("You rolled a null.");
    }

    public static void displayCard(CardsValue c1){
        System.out.println("You drew following card: " + c1);
    }

    public static void displayPoints(Player P1){
        System.out.println("You've got " + P1.getPoints() + " Points.");
    }

    private static void printInner(diceNumber d1, int i){
        if(d1 == diceNumber.SIX) System.out.print(" o   o ");
        else if(d1 == diceNumber.ONE && i == 2) System.out.print("   o   ");
        else if(d1 == diceNumber.TWO && i == 1) System.out.print("     o ");
        else if(d1 == diceNumber.TWO && i == 3) System.out.print(" o     ");
        else if(d1 == diceNumber.THREE && i == 1) System.out.print("     o ");
        else if(d1 == diceNumber.THREE && i == 2) System.out.print("   o   ");
        else if(d1 == diceNumber.THREE && i == 3) System.out.print(" o     ");
        else if(d1 == diceNumber.FOUR && (i == 3||i==1)) System.out.print(" o   o ");
        else if(d1 == diceNumber.FIVE && (i == 3||i==1)) System.out.print(" o   o ");
        else if(d1 == diceNumber.FIVE && i == 2) System.out.print("   o   ");
        else System.out.print("       ");
    }

    private static void printUpper(){
        System.out.println(" _________");
    }
    private static void printLeftBox(){
        System.out.print("| ");
    }
    private static void printRightBox(){
        System.out.println(" |");
    }
    private static void printUnder(byte b){
        System.out.println("|_________| Dice Nr: " + b);
    }
}
