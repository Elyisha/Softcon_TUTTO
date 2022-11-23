package Turn;

public class CloverleafTurn extends AbstractTurn{
    public static TurnResult cloverleafTurn() {
        boolean gameWon = false;

        TurnResult result = getRoll();
        if (result.isTutto) {
            TurnResult resultsecond = getRoll();
            if (resultsecond.isTutto) gameWon = true;
        }

        return new TurnResult((short)0, gameWon);
    }

}
