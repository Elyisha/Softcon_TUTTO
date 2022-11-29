package Cards;
import Gameflow.TurnResult;

public class StopCard extends Card implements CardInterface {
    public StopCard() {super(CardsValue.STOP);} //inherits Constructor from abstract class

    public TurnResult getRoll() {
        System.out.println("Bad luck you've got a Stop card which means its not your turn anymore");
        return (new TurnResult((short) 0,false));
    }

}
