package Cards;
import Gameflow.TurnResult;

public class PlusMinusCard extends Card implements CardInterface {
    public PlusMinusCard() {super(CardsValue.PLUSMINUS);} //inherits Constructor from abstract class

    public TurnResult getRoll() {
        short roundPoints = 0;
        TurnResult result = getAbstractRoll();

        if (result.isTutto) {
            roundPoints = 1000;
        }
        return new TurnResult(roundPoints, result.isTutto);
    }

}