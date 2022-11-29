package Cards;
import Gameflow.TurnResult;

public class CloverleafCard extends Card implements CardInterface {
    public CloverleafCard() {
        super(CardsValue.CLOVERLEAF); //inherits Constructor from abstract class
    }
    public TurnResult getRoll() {
        boolean gameWon = false;

        TurnResult result = getAbstractRoll();
        if (result.isTutto) {
            TurnResult secondresult = getRoll();
            if (secondresult.isTutto) gameWon = true;
        }

        return new TurnResult((short) 0, gameWon);

    }

}
