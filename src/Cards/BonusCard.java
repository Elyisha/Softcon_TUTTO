package Cards;
import Gameflow.TurnResult;
public class BonusCard extends Card implements CardInterface {
    public BonusCard(CardsValue cardsValue) {super(cardsValue);} //inherits Constructor from abstract class

    public void controlTurn(){
        //todo
    }
    public TurnResult getRoll() { //todo nach controlTurn: das hier private machen
        short roundPoints = 0;

        TurnResult result = getAbstractRoll();
        roundPoints += result.points;
        if (result.isTutto) {
            switch (aCardsValue){
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
