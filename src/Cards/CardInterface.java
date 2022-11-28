package Cards;

import Gameflow.TurnResult;

public interface CardInterface {
    public void controlTurn();
    public TurnResult getRoll();
    public CardsValue getValue();
 }
