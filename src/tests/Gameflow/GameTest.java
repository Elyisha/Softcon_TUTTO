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
            return new String[]{"John", "Abby", "Harris"};
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
        public Card getCard() {
            return new BonusCard(CardsValue.BONUS600);
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
        String expected = """
                Abby its your turn: \r
                You drew following card: BONUS600\r
                (So far you have managed to reach 1100 Points in this round)\r
                Abby has won the game! Good Job\r
                1000 Points were required to win this round.\r
                Abby managed to reach 1100 Points\r
                Harris managed to reach 0 Points\r
                John managed to reach 0 Points""";
        boolean rightMessage = outputStreamCaptor.toString().trim().contains(expected);
        assertTrue(rightMessage);
    }



}