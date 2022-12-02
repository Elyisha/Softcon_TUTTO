package Input;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Arrays;

import static Input.Input.*;
import static org.junit.jupiter.api.Assertions.*;

class InputTest {
    private final InputStream standardIn = System.in;

    @AfterEach
    void teardown(){System.setIn(standardIn);}      //restores stream


    @Test
    void takeNamesTest(){
        ByteArrayInputStream testInput = new ByteArrayInputStream("Hans, Peter".getBytes());
        System.setIn(testInput);
        String[] expected = {"Hans", "Peter"};
        String[] actual = takeNames();
        assertTrue(Arrays.equals(expected, actual));
    }

    @Test
    void takeMaxPointsTest(){
        ByteArrayInputStream testInput = new ByteArrayInputStream("2500".getBytes());
        System.setIn(testInput);
        short expected = 2500;
        short actual = takeMaxPoints();
        assertEquals(expected, actual);
    }


    @Test
    void askUserDRTest(){
        ByteArrayInputStream testInput = new ByteArrayInputStream("R".getBytes());
        System.setIn(testInput);
        boolean expected = true;
        boolean actual = askUserDR();
        assertEquals(expected, actual);
    }

    @Test
    void askUserDRTest2(){
        ByteArrayInputStream testInput = new ByteArrayInputStream("D".getBytes());
        System.setIn(testInput);
        boolean expected = false;
        boolean actual = askUserDR();
        assertEquals(expected, actual);
    }
    @Test
    void askUserRETest(){
        ByteArrayInputStream testInput = new ByteArrayInputStream("E".getBytes());
        System.setIn(testInput);
        boolean expected = false;
        boolean actual = askUserRE();
        assertEquals(expected, actual);
    }
    @Test
    void askUserRETest2(){
        ByteArrayInputStream testInput = new ByteArrayInputStream("R".getBytes());
        System.setIn(testInput);
        boolean expected = true;
        boolean actual = askUserRE();
        assertEquals(expected, actual);
    }
}