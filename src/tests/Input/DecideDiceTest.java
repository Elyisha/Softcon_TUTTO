package Input;

import Dices.Dice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static Input.DecideDice.decideDice;
import static Input.DecideDice.straightDecideDice;
import static org.junit.jupiter.api.Assertions.*;

class DecideDiceTest {
    private InputStream sysInBackup;

    @BeforeEach
    void Setup(){
        sysInBackup = System.in; // backup System.in to restore it later
        ByteArrayInputStream in = new ByteArrayInputStream("2,3,4".getBytes());
        System.setIn(in);
    }
    @AfterEach
    void teardown(){
        System.setIn(sysInBackup);
    }
    public static ArrayList<Dice> SetupDiceArray() {
        ArrayList<Dice> dices = new ArrayList<>();
        for(int i = 0; i < 6; ++i) {
            dices.add(new Dice());
            if(i == 0)
                do {
                    dices.set(i, new Dice());
                } while(dices.get(i).getDiceNumber().ordinal() != 2);
            else if(i == 4)
                do {
                    dices.set(i, new Dice());
                } while(dices.get(i).getDiceNumber().ordinal() != 3);
            else if(i == 5)
                do {
                    dices.set(i, new Dice());
                } while(dices.get(i).getDiceNumber().ordinal() != 5);
            else
                do {
                    dices.set(i, new Dice());
                } while(dices.get(i).getDiceNumber().ordinal() != 4);
        }
        return dices;
    }
/*
    @Test
    void decideDiceTest() {
        ArrayList<Dice> dices = SetupDiceArray();
        decideDice(dices,false);
        for (int i = 1; i < 4; ++i) {
            assertTrue(dices.get(i).isAside());
        }
    }
*/
    @Test
    void decideDiceFireworksTest() {
        ArrayList<Dice> dices = SetupDiceArray();
        int actual = decideDice(dices,true);
        int expected = 500;
        assertEquals(expected, actual);
    }

    @Test
    void straightDecideDiceTest() {
        ByteArrayInputStream in = new ByteArrayInputStream("1,2".getBytes());
        System.setIn(in);
        ArrayList<Dice> dices = SetupDiceArray();
        straightDecideDice(dices);
        for (int i = 0; i < 2; ++i) {
            assertTrue(dices.get(i).isAside());
        }
    }
}