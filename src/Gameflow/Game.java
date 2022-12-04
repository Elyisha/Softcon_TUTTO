package Gameflow;

import Cards.*;
import Input.Input;

import java.util.Arrays;

public class Game {
    //Singleton Game Object Field:
    private static Game uniqueInstance;
    private final short maxPoints;
    private final Player[] players;


    //singleton implementation of Constructor (can only be called by Game.getInstance()) :
    private Game(){
        maxPoints = Input.takeMaxPoints();
        String[] names = Input.takeNames();
        // Sort names array to get the alphabetical starting order
        Arrays.sort(names);

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
        Deck aDeck = Deck.getInstance(); //Card supply
        boolean isOver = false;
        while(!isOver){ //breaks if a player won the game
            for(Player aPlayer: players){
                //Ask Player whether he wants to see his score
                System.out.println(aPlayer.getName()+" its your turn: ");
                while(!Input.askUserDR()){Display.displayPoints(aPlayer.getPoints(), maxPoints);}

                //Game start:
                    //Variables to decide whether points can be added or not
                    short currentPoints = 0;
                    boolean pointsADD = false;
                    do{ //this loop ends when a player has no more dices to choose or he decides to stop his round after a tutto
                        Card aCard = aDeck.getCard();
                        Display.displayCard(aCard.getValue());
                        TurnResult result = aCard.getRoll();
                        currentPoints += result.points;
                        if(aCard.getValue().ordinal() == 5 && result.isTutto){
                            subtractThousand(aPlayer);
                        }
                        // In case cloverleafTurn and tutto --> game is over:
                        if(aCard.getValue().ordinal() == 0 && result.isTutto){
                            aPlayer.addPoints((short) (maxPoints - aPlayer.getPoints())); // aPlayer gets required points to win game
                            break;
                        }

                        if(result.points != 0){pointsADD = true;}
                        if(!result.isTutto){break;}
                        //when a player managed to get a Tutto it is up to him whether he wants to make a new round:
                        else{Display.displayCurrentPointsAfterTutto(result.points);}
                    }while(Input.askUserRE());

                    if(pointsADD){aPlayer.addPoints(currentPoints);}
                    if(aPlayer.playerWon()){
                        printEndNote(aPlayer);
                        isOver = true;
                        break;
            }
            }
        }
    }

    private void subtractThousand(Player aPlayer){
        Player leader = this.players[0];
        //find player with the most # points
        for(Player P: this.players){
            if(P.getPoints() > leader.getPoints()){
                leader = P;
            }
        }
        short leadersPoints = leader.getPoints();
        //second for each loop since when there are several leaders and all aren't the current player and
        // all have more than 1000 points each of the leaders looses 1000
        for(Player P: this.players){
            if(P.getPoints() == leadersPoints && P != aPlayer){
                P.subtract1000();
            }
        }
    }

    private void printEndNote(Player Winner){
        System.out.println(Winner.getName() + " has won the game! Good Job");
        System.out.println(this.maxPoints + " Points were required to win this round.");
        for(Player P: players){
            System.out.println(P.getName() + " managed to reach " + P.getPoints() + " Points");
        }
    }

}
