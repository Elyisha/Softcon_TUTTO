package Turn;

import Cards.CardsValue;
public class TwoTimesTurn extends AbstractTurn{
    public TurnResult twoTimesTurn() {
        short roundPoints = 0;
        TurnResult result = getRoll();

        roundPoints += result.points;
        if (result.isTutto) roundPoints += result.points; //double it if tutto
        return new TurnResult(roundPoints, result.isTutto);
    }

}
