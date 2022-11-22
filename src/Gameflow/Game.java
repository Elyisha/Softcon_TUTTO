package Gameflow;

import Cards.Card;
import Cards.CardsValue;
import Cards.Deck;
import Turn.*;

public class Game {
    //Singleton Game Object Field:
    private static Game uniqueInstance;
    private final short maxPoints;
    private Player[] players; // = new Player[#


    //singleton implementation of Constructor (can only be called by Game.getInstance()) :
    private Game(){
        maxPoints = Input.takeMaxPoints();
        String[] names = Input.takeNames(); //get Player-names

        // The Player array needs to be initialized with the number Players (= len of names-array) :
        this.players = new Player[names.length];

        //For loop to go through names and thereby initialize players
        for(int i = 0; i< names.length; i++){
            players[i] = new Player(names[i], maxPoints);
        }
    }

    //synchronized getInstance Method to prevent different threads from creating two singleton Objects:
    public static synchronized Game getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Game();
        }
        return uniqueInstance;
    }

    public void startGameFlow(){
        //boolean isOver = false; //changes to true the moment a player reaches maxPoints
        Deck aDeck = new Deck(); //Card supply
        boolean isOver = false;
        while(!isOver){ //breaks if a player won the game
            for(Player aPlayer: players){
                //Ask Player whether he wants to see his score
                System.out.println(aPlayer.getName()+":");
                if(!Input.askUserDR()){
                    System.out.println("You Have got " + aPlayer.getPoints() + " Points.");
                    System.out.println((maxPoints-aPlayer.getPoints()) + " more to go.");
                }
                    //Variables to decide whether points can be added or not
                    short currentPoints = 0;
                    boolean pointsADD = false;
                    while(true){ //this loop ends when a player has no more dices to choose or he decides to stop his round after a tutto
                        Card aCard = new Card(CardsValue.BONUS200); //aDeck.getCard();
                        TurnResult result = makeTurn(aPlayer, aCard.getValue());
                        currentPoints += result.points;
                        //aPlayer.addPoints(result.points);
                        if(result.points != 0){pointsADD = true;}
                        if(!result.isTutto){break;}
                    }
                    if(pointsADD){aPlayer.addPoints(currentPoints);}
                    if(aPlayer.playerWon()){
                        System.out.println(aPlayer.getName() + " has won the game! Good Job");
                        isOver = true;
                        break;
            }
            }

        }
    }

    private TurnResult makeTurn(Player player, CardsValue aCardValue){
        //everything above .ordinal 6 are Bonus cards
        if(aCardValue.ordinal() > 5){
            TurnResult resultRound = BonusTurn.bonusTurn(aCardValue); //Pointer to a field ?
            return resultRound;
        }

        return new TurnResult((short) 3, true);
    }


}
