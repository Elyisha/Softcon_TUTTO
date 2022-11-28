package Cards;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    void hasRightFrequency(){
        Deck d1 = Deck.getInstance();
        int[] Frequency = new int[11];
        int[] rightFrequency = new int[]{1,5,10,5,5,5,5,5,5,5,5};
        for(int i = 0; i<56; i++){
            Frequency[d1.getCard().getValue().ordinal()] += 1;
        }
        for(int i = 0; i<10;i++){
            assertEquals(rightFrequency[i], Frequency[i]);
        }
    }
}