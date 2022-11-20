package Turn;

public class FireworksTurn extends AbstractTurn{

    //TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO TODO
    public TurnResult fireworksTurn(short currentPoints) {
        short newPoints = currentPoints;
        boolean nullThrow = true;
        while (nullThrow) {
            TurnResult result = getRoll();
            newPoints += result.a;
            //TODO UPDATE WHILE VARIABLE
            //TODO CORRECTLY RETURN EEVERY CORRECT VALUE - SEE ASSIGNMENT DESCRIPTION

        }
        if (result.b) newPoints += newPoints; //double it if tutto
        return new TurnResult(newPoints, result.b);
    }

}
