package Turn;
import Cards.CardsValue;

public class BonusTurn extends AbstractTurn {
    public TurnResult bonusTurn(short currentPoints, CardsValue cardType) {
        short newPoints = currentPoints;

        Display.DisplayCard(cardType);
        TurnResult result = getRoll();

        newPoints += result.a;
        if (result.b) {
            switch (cardType){
                case BONUS200 -> newPoints += 200;
                case BONUS300 -> newPoints += 300;
                case BONUS400 -> newPoints += 400;
                case BONUS500 -> newPoints += 500;
                case BONUS600 -> newPoints += 600;
            }
        }
        return new TurnResult(newPoints, result.b);
    }
}
