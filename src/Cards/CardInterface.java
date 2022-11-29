package Cards;

import Gameflow.TurnResult;

public interface CardInterface {
    public TurnResult getRoll();
    public CardsValue getValue();
 }
