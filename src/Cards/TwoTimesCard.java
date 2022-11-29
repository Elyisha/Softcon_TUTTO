package Cards;
import Gameflow.TurnResult;

public class TwoTimesCard extends Card implements CardInterface {
    public TwoTimesCard() {super(CardsValue.TIMESTWO);} //inherits Constructor from abstract class

    public TurnResult getRoll() {
        short roundPoints;
        TurnResult result = getAbstractRoll();
        roundPoints = result.points;
        if (result.isTutto) roundPoints += result.points; //double it if tutto
        return new TurnResult(roundPoints, result.isTutto);
    }

}
