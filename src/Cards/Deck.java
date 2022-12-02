package Cards;

import Gameflow.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> aCards = new ArrayList<>();
    //cards that are drawn from the deck are stored in usedCards so that the Cardobjects can be reused
    private List<Card> usedCards = new ArrayList<>();
    //private static Deck uniqueInstance;



    // Since a Deck is going to be "recycled" when all cards are drawn there should not be two decks at the same time
    // -> Singleton

    public Deck() {
        //add the required number of cards to get the wanted frequency...
        aCards.add(new CloverleafCard());
        for(int i = 0; i<5;i++){aCards.add(new FireworksCard());}
        for(int i = 0; i<10;i++){aCards.add(new StopCard());}
        for(int i = 0; i<5;i++){aCards.add(new StraightCard());}
        for(int i = 0; i<5;i++){aCards.add(new PlusMinusCard());}
        for(int i = 0; i<5;i++){aCards.add(new TwoTimesCard());}
        for(int i = 0; i<5;i++){aCards.add(new BonusCard(CardsValue.BONUS200));}
        for(int i = 0; i<5;i++){aCards.add(new BonusCard(CardsValue.BONUS300));}
        for(int i = 0; i<5;i++){aCards.add(new BonusCard(CardsValue.BONUS400));}
        for(int i = 0; i<5;i++){aCards.add(new BonusCard(CardsValue.BONUS500));}
        for(int i = 0; i<5;i++){aCards.add(new BonusCard(CardsValue.BONUS600));}
        //... and shuffle them
        Collections.shuffle(aCards);
    }
    /*
    public static synchronized Deck getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Deck();
        }
        return uniqueInstance;
    }

     */
    public Card getCard() {
        //System.out.println(aCards.size());
        if (!isEmpty()) {
            Card aCard = aCards.get(0);
            usedCards.add(aCard);
            aCards.remove(0);
            //System.out.println(aCards.size());
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
