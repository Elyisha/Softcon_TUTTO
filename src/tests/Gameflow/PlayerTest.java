package Gameflow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {


    @Test
    void playerWonTest() {
        Player p1 = new Player("Hank S.", (short) 1000);
        p1.addPoints((short) 900);
        assertFalse(p1.playerWon());
        p1.addPoints((short) 100);
        assertTrue(p1.playerWon());
        p1.addPoints((short) 100);
        assertTrue(p1.playerWon());
    }

    @Test
    void addPointsTest() {
        Player p1 = new Player("Skylar W.", (short) 1000);
        assertEquals(p1.getPoints(), (short) 0) ;
        p1.addPoints((short) 200);
        assertEquals(p1.getPoints(), (short) 200) ;
    }

    @Test
    void getName() {
        Player p1 = new Player("Jesse P." , (short) 1000);
        assertEquals("Jesse P." , p1.getName());
    }

    @Test
    void getPoints() {
        Player P1 = new Player("Tuco S.", (short) 1000);
        P1.addPoints((short) 1000);
        assertEquals((short) 1000, P1.getPoints());
    }

    @Test
    void subtract1000() {
        Player P1 = new Player("Holly W.", (short) 2000);
        Player P2 = new Player("Ted B.", (short) 2000);
        P1.addPoints((short) 1200);
        P2.addPoints((short) 200);
        P1.subtract1000();
        P2.subtract1000();
        assertEquals((short) 200, P1.getPoints());
        assertEquals((short) 0, P2.getPoints());
    }
}