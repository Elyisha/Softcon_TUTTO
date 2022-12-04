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
        String expected = """
                _________\r
                |         |\r
                |    o    |\r
                |         |\r
                |_________| Dice Nr: 1""";
        Display.displayDice(diceNumber.ONE, (byte) 1);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
    @Test
    void diceTwoDisplayTest(){
        String expected = """
                _________\r
                |      o  |\r
                |         |\r
                |  o      |\r
                |_________| Dice Nr: 2""";
        Display.displayDice(diceNumber.TWO, (byte) 2);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void diceThreeDisplayTest(){
        String expected = """
                _________\r
                |      o  |\r
                |    o    |\r
                |  o      |\r
                |_________| Dice Nr: 3""";
        Display.displayDice(diceNumber.THREE, (byte) 3);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
    @Test
    void diceFourDisplayTest(){
        String expected = """
                _________\r
                |  o   o  |\r
                |         |\r
                |  o   o  |\r
                |_________| Dice Nr: 4""";
        Display.displayDice(diceNumber.FOUR, (byte) 4);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void diceFiveDisplayTest(){
        String expected = """
                _________\r
                |  o   o  |\r
                |    o    |\r
                |  o   o  |\r
                |_________| Dice Nr: 5""";
        Display.displayDice(diceNumber.FIVE, (byte) 5);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
    @Test
    void diceSixDisplayTest(){
        String expected = """
                _________\r
                |  o   o  |\r
                |  o   o  |\r
                |  o   o  |\r
                |_________| Dice Nr: 6""";
        Display.displayDice(diceNumber.SIX, (byte) 6);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void displayCardsTest(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<11 ; i++){
            Display.displayCard(CardsValue.values()[i]);
            if(i<10) {
                sb.append("You drew following card: ").append(CardsValue.values()[i]).append("\r\n");
            }
            else{
                sb.append("You drew following card: ").append(CardsValue.values()[i]);
            }
        }
        String expected = sb.toString();
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void displayPointsTest(){
        String expected = "You Have got 600 Points.\r\n" +
                "400 more to go.";
        Display.displayPoints((short) 600, (short) 1000);
        assertEquals(expected, outputStreamCaptor.toString().trim());

    }

    @Test
    void displayCurrentPointsAfterTuttoTest(){
        String expected = "(You managed to reach 600 Points in this round, thanks to the accomplished Tutto.)";
        Display.displayCurrentPointsAfterTutto((short) 600);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}