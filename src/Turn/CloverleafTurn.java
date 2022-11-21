package Turn;

public class CloverleafTurn extends AbstractTurn{
    public TurnResult cloverleafTurn(short currentPoints) {
        boolean gameWon = false;

        TurnResult result = getRoll();
        if (result.isTutto) {
            TurnResult resultsecond = getRoll();
            if (resultsecond.isTutto) gameWon = true;
        }

        return new TurnResult(currentPoints, gameWon);
    }

}
