package Turn;

public class PlusMinusTurn extends AbstractTurn{
    public static TurnResult plusMinusTurn(short currentPoints) {
        short newPoints = currentPoints;
        TurnResult result = getRoll();

        if (result.isTutto) {
            newPoints += 1000;
        }
        return new TurnResult(newPoints, result.isTutto);
    }

}
