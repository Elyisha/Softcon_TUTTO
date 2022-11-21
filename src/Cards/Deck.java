package Cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> aCards = new ArrayList<>();
    //cards that are drawn from the deck are stored in usedCards so that the Cardobjects can be reused
    private List<Card> usedCards = new ArrayList<>();

    private int numCards;

    public Deck() {
        for (int i = 0; i < 56; i++) {
            if (i < 1) {
                aCards.add(new Card(CardsValue.CLOVERLEAF));
            } else if (i < 6) {
                aCards.add(new Card(CardsValue.FIREWORK));
            } else if (i < 16) {
                aCards.add(new Card(CardsValue.STOP));
            } else if (i < 21) {
                aCards.add(new Card(CardsValue.STRAIGHT));
            } else if (i < 26) {
                aCards.add(new Card(CardsValue.PLUSMINUS));
            } else if (i < 31) {
                aCards.add(new Card(CardsValue.TIMESTWO));
            } else if (i < 36) {
                aCards.add(new Card(CardsValue.BONUS200));
            } else if (i < 41) {
                aCards.add(new Card(CardsValue.BONUS300));
            } else if (i < 46) {
                aCards.add(new Card(CardsValue.BONUS400));
            } else if (i < 51) {
                aCards.add(new Card(CardsValue.BONUS500));
            } else {
                aCards.add(new Card(CardsValue.BONUS600));
            }
        }
        Collections.shuffle(aCards);
    }


    public Card getCard() {
        System.out.println(aCards.size());
        if (!isEmpty()) {
            Card aCard = aCards.get(0);
            usedCards.add(aCard);
            aCards.remove(0);
            System.out.println(aCards.size());
            return aCard;
        } else {
            //In case that there are no more cards left in the deck
            //The ArrayList usedCards is going to be aCards and aCards
            //is going to be shuffled
            List<Card> copy = new ArrayList<>();
            copy = aCards;
            aCards = usedCards;
            usedCards = copy;
            Collections.shuffle(aCards);
            return getCard();
        }
    }

    private Boolean isEmpty() {
        return (aCards.size() == 0);
    }
}
