package Cards;

public class Card {
    private CardsValue aCardsValue;

    public Card(CardsValue cardsValue) {
        aCardsValue = cardsValue;
    }

    public CardsValue getValue(){
        return aCardsValue;
    }
}
