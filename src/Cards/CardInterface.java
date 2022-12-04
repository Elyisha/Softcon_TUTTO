package Cards;

import Gameflow.TurnResult;

public interface CardInterface {
    TurnResult getRoll();
    CardsValue getValue();
 }
