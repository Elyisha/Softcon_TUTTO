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
                        Card aCard = aDeck.getCard();
                        Display.displayCard(aCard.getValue());
                        TurnResult result = makeTurn(aCard.getValue());
                        if(aCard.getValue().ordinal() == 5 && result.isTutto){
                            subtractThousand(aPlayer);
                        }
                        currentPoints += result.points;

                        // In case cloverleafTurn and tutto --> game is over:
                        if(aCard.getValue().ordinal() == 0 && result.isTutto){
                            aPlayer.addPoints((short) (maxPoints - aPlayer.getPoints()));
                            break;
                        }

                        //aPlayer.addPoints(result.points);
                        if(result.points != 0){pointsADD = true;}
                        if(!result.isTutto){break;}
                        if(!Input.askUserRE()){break;}

                    }
                    if(pointsADD){aPlayer.addPoints(currentPoints);}
                    if(aPlayer.playerWon()){
                        printEndNote(aPlayer);
                        isOver = true;
                        break;
            }
            }
        }
    }

    private TurnResult makeTurn(CardsValue aCardValue){
        //everything above .ordinal 6 are Bonus cards
        TurnResult resultRound;
        if(aCardValue.ordinal() > 5){
            resultRound = BonusTurn.bonusTurn(aCardValue);
        }
        else if(aCardValue.ordinal() == 0){
            resultRound = CloverleafTurn.cloverleafTurn();
        }
        else if(aCardValue.ordinal() == 1){
            resultRound = FireworksTurn.fireworksTurn();
        }
        else if(aCardValue.ordinal() == 2){
            System.out.println("Bad luck you've got a Stop card which means its not your turn anymore");
            resultRound = new TurnResult((short) 0,false);
        }
        else if(aCardValue.ordinal() == 3){
            resultRound = StraightTurn.getRoll();
        }
        else if(aCardValue.ordinal() == 4){
            resultRound = TwoTimesTurn.twoTimesTurn();
        }
        else{
            resultRound = PlusMinusTurn.plusMinusTurn((short) 12); //wieso plusminus noch mit input ?
        }
        return resultRound;
    }

    private void subtractThousand(Player player){
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
            if(P.getPoints() == leadersPoints && P != player && P.getPoints() > 1000){
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
