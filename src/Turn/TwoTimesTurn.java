package Turn;

import Cards.CardsValue;
public class TwoTimesTurn extends AbstractTurn{
    public TurnResult twoTimesTurn(short currentPoints) {
        short newPoints = currentPoints;
        TurnResult result = getRoll();

        newPoints += result.a;
        if (result.b) newPoints += newPoints; //double it if tutto
        return new TurnResult(newPoints, result.b);
    }

}
