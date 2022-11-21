package Turn;

import Cards.CardsValue;
public class TwoTimesTurn extends AbstractTurn{
    public TurnResult twoTimesTurn(short currentPoints) {
        short newPoints = currentPoints;
        TurnResult result = getRoll();

        newPoints += result.points;
        if (result.isTutto) newPoints += result.points; //double it if tutto
        return new TurnResult(newPoints, result.isTutto);
    }

}
