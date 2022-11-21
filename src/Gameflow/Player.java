package Gameflow;

public class Player {
    private final String name;
    private short points = 0;
    private short maxPoints; // Player class needs to have this information to provide playerWon method

    public Player(String playerName, short maxPoints){
        name = playerName;
        this.maxPoints = maxPoints;
    }

    // How does Game.Player-class know how many points are required to win ? has Game-Class getter method for max points

    public Boolean playerWon(){
        return points > maxPoints;
    }


    //Is this method needed ? and if yes by whom ?
    public void addPoints(short aShort){
        points += aShort;
    }

    //Encapsulation wise safe because name final ? Or should a copy be performed ?
    public String getName(){
        return name;
    }

    public short getPoints(){
        return points;
    }
}
