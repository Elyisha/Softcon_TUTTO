package Cards;
import Turn.TurnResult;

public class StopCard extends Card implements CardInterface {
    public StopCard() {super(CardsValue.STOP);} //inherits Constructor from abstract class

    public void controlTurn(){
        //todo
    }
    public TurnResult getRoll() { //todo nach controlTurn: das hier private machen
        return (new TurnResult((short) 0,false));
    }

}
