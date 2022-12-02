package Gameflow;
import static org.junit.jupiter.api.Assertions.*;
import Cards.*;
import Input.Input;
import mockit.Mock;
import mockit.MockUp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


class GameTest {
    private final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void installMockClasses() {
        new GameSetUpMock();
        new BonusTurn();
        new TurnResultMock();
    }
    @BeforeAll
    public static void setUp() {System.setOut(new PrintStream(outputStreamCaptor));}

    public static class GameSetUpMock extends MockUp<Input> {
        @Mock
        public static Short takeMaxPoints(){
            return (short) 1000;
        }
        @Mock
        public static String[] takeNames(){
            String[] names = {"John", "Abby", "Harris"};
            return names;
        }
        @Mock
        public static boolean askUserDR(){
            return true;
        }
        @Mock
        public static boolean askUserRE(){
            return false;
        }
    }

    public static class BonusTurn extends MockUp<Deck> {
        @Mock
        public Card getCard(){
            Card c1 = new BonusCard(CardsValue.BONUS600);
            return c1;
        }
    }

    public static class TurnResultMock extends MockUp<BonusCard> {
        @Mock
        public TurnResult getRoll(){
            return new TurnResult((short) 1100, true);
        }
    }

    @Test
    void singletonTest(){
        Game G1 = Game.getInstance();
        Game G2 = Game.getInstance();
        assertSame(G1,G2);
    }

    @Test
    void testTurn(){
        Game G1 = Game.getInstance();
        G1.startGameFlow();
        StringBuilder sb = new StringBuilder();
        sb.append("Abby its your turn: \r\n" +
                "You drew following card: BONUS600\r\n" +
                "(So far you have managed to reach 1100 Points in this round)\r\n" +
                "Abby has won the game! Good Job\r\n" +
                "1000 Points were required to win this round.\r\n" +
                "Abby managed to reach 1100 Points\r\n" +
                "Harris managed to reach 0 Points\r\n" +
                "John managed to reach 0 Points");
        String expected = sb.toString();
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }



}