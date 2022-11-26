package Turn;
import Cards.CardsValue;
import Gameflow.Display;

public class BonusTurn extends AbstractTurn {

    public static TurnResult bonusTurn(CardsValue cardType) {
        short roundPoints = 0;

        TurnResult result = getRoll();
        roundPoints += result.points;
        if (result.isTutto) {
            switch (cardType){
                case BONUS200 -> roundPoints += 200;
                case BONUS300 -> roundPoints += 300;
                case BONUS400 -> roundPoints += 400;
                case BONUS500 -> roundPoints += 500;
                case BONUS600 -> roundPoints += 600;
            }
        }
        return new TurnResult(roundPoints, result.isTutto);
    }
}
