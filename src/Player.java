public class Player {
    private final String name;
    private byte points = 0;

    public Player(String playerName){
        name = playerName;
    }

    // How does Player-class know how many points are required to win ? has Game-Class getter method for max points
   /**
    public Boolean playerWon(Game G1){
        byte gamePoints;
        gamePoints = G1.getGamePoints();
        return points > gamePoints;
    }
    */

    //Is this method needed ? and if yes by whom ?
    public void addPoints(byte aByte){
        points += aByte;
    }

    //Encapsulation wise safe because name final ? Or should a copy be performed ?
    public String getName(){
        return name;
    }

    public byte getPoints(){
        return points;
    }
}
