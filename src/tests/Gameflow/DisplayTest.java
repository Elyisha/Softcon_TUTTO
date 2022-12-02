package Gameflow;
import Cards.CardsValue;
import Dices.diceNumber;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class DisplayTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @BeforeEach
    public void setUp() {System.setOut(new PrintStream(outputStreamCaptor));}

    @Test
    void diceOneDisplayTest(){
        StringBuilder sb = new StringBuilder();
        sb.append("_________\r\n");
        sb.append("|         |\r\n");
        sb.append("|    o    |\r\n");
        sb.append("|         |\r\n");
        sb.append("|_________| Dice Nr: 1");
        String expected = sb.toString();
        Display.displayDice(diceNumber.ONE, (byte) 1);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
    @Test
    void diceTwoDisplayTest(){
        StringBuilder sb = new StringBuilder();
        sb.append("_________\r\n");
        sb.append("|      o  |\r\n");
        sb.append("|         |\r\n");
        sb.append("|  o      |\r\n");
        sb.append("|_________| Dice Nr: 2");
        String expected = sb.toString();
        Display.displayDice(diceNumber.TWO, (byte) 2);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void diceThreeDisplayTest(){
        StringBuilder sb = new StringBuilder();
        sb.append("_________\r\n");
        sb.append("|      o  |\r\n");
        sb.append("|    o    |\r\n");
        sb.append("|  o      |\r\n");
        sb.append("|_________| Dice Nr: 3");
        String expected = sb.toString();
        Display.displayDice(diceNumber.THREE, (byte) 3);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
    @Test
    void diceFourDisplayTest(){
        StringBuilder sb = new StringBuilder();
        sb.append("_________\r\n");
        sb.append("|  o   o  |\r\n");
        sb.append("|         |\r\n");
        sb.append("|  o   o  |\r\n");
        sb.append("|_________| Dice Nr: 4");
        String expected = sb.toString();
        Display.displayDice(diceNumber.FOUR, (byte) 4);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void diceFiveDisplayTest(){
        StringBuilder sb = new StringBuilder();
        sb.append("_________\r\n");
        sb.append("|  o   o  |\r\n");
        sb.append("|    o    |\r\n");
        sb.append("|  o   o  |\r\n");
        sb.append("|_________| Dice Nr: 5");
        String expected = sb.toString();
        Display.displayDice(diceNumber.FIVE, (byte) 5);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
    @Test
    void diceSixDisplayTest(){
        StringBuilder sb = new StringBuilder();
        sb.append("_________\r\n");
        sb.append("|  o   o  |\r\n");
        sb.append("|  o   o  |\r\n");
        sb.append("|  o   o  |\r\n");
        sb.append("|_________| Dice Nr: 6");
        String expected = sb.toString();
        Display.displayDice(diceNumber.SIX, (byte) 6);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void displayCardsTest(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<11 ; i++){
            Display.displayCard(CardsValue.values()[i]);
            if(i<10) {
                sb.append("You drew following card: " + CardsValue.values()[i] + "\r\n");
            }
            else{
                sb.append("You drew following card: " + CardsValue.values()[i]);
            }
        }
        String expected = sb.toString();
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void displayPointsTest(){
        StringBuilder sb = new StringBuilder();
        sb.append("You Have got 600 Points.\r\n");
        sb.append("400 more to go.");
        String expected = sb.toString();
        Display.displayPoints((short) 600, (short) 1000);
        assertEquals(expected, outputStreamCaptor.toString().trim());

    }

    @Test
    void displayCurrentPointsAfterTuttoTest(){
        String expected = "(So far you have managed to reach 600 Points in this round)";
        Display.displayCurrentPointsAfterTutto((short) 600);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}