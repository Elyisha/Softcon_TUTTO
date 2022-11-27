package Cards;
import Turn.TurnResult;

public class TwoTimesCard extends Card implements CardInterface {
    public TwoTimesCard() {super(CardsValue.TIMESTWO);} //inherits Constructor from abstract class

    public void controlTurn(){
        //todo
    }
    public TurnResult getRoll() { //todo nach controlTurn: das hier private machen
        short roundPoints = 0;
        TurnResult result = getAbstractRoll();

        roundPoints += result.points;
        if (result.isTutto) roundPoints += result.points; //double it if tutto
        return new TurnResult(roundPoints, result.isTutto);
    }

}
