package Cards;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    @Test
    void hasRightFrequency(){
        Deck d1 = Deck.getInstance();
        int[] Frequency = new int[11];
        int[] expectedFrequency = new int[]{1,5,10,5,5,5,5,5,5,5,5};
        for(int i = 0; i<56; i++){
            Frequency[d1.getCard().getValue().ordinal()] += 1;
        }
        for(int i = 0; i<10;i++){
            assertEquals(expectedFrequency[i], Frequency[i]);
        }
    }


    @Test

    void deckDrawsCardAfterEmpty(){
        Deck d1 = Deck.getInstance();
        //draw all cards...
        for(int i = 0; i < 56;i++){
            Card c = d1.getCard();
        }
        //... and check whether refill worked through another draw
        Card c1 = d1.getCard();
        assertNotNull(c1);
        for(int i = 0; i < 55;i++){Card c = d1.getCard();}
    }


}