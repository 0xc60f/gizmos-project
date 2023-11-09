package Logic;

import java.util.*;
import java.util.stream.IntStream;

import Classes.*;

public class Game
{
    private ArrayList<Player> playerList;
    private Deck deck;
    private Player currentPlayer;
    private boolean gameEnd, gameStart;
    private EnergyDispenser energyDispenser;

    /**
     * instantiates all attributes related to the game
     */
    public Game()
    {
        playerList = new ArrayList<Player>();
        IntStream.rangeClosed(1, 4).forEach(i -> playerList.add(new Player(i)));
        deck = new Deck();
        currentPlayer = playerList.get(determineFirstPlayer()); //not sure of
        gameEnd = false;
        gameStart = true;
        energyDispenser = new EnergyDispenser();

    }

    /**
     * Is the main game loop that runs the entire game
     */
    public void play()
    {

    }

    /**
     * SHuffles the ArrayList with players and returns the first player to determine
     * the first player to start the round
     */
    public int determineFirstPlayer() {
        Collections.shuffle(playerList);
        return playerList.get(0).getPlayerNumber();
    }

    /**
     * returns a list of all players' scores in corresponding order
     */
    public ArrayList<Integer> calculateScores()
    {
        return null;
    }

    /**
     * returns the currentPlayer
     */
    public Player getCurrentPlayer()
    {
        return currentPlayer;
    }

    /**
     This method sets the order and returns the current player, and if its the last player
     then it starts back to 1 and will go through 1-4
     */
    public int setNextPlayer()
    {
        int currentPlayerNum = currentPlayer.getPlayerNumber();
        if (currentPlayerNum == 4)
        {
            currentPlayerNum = 1;
            return currentPlayerNum;
        }
        currentPlayerNum++;
        return currentPlayerNum;
    }

    /**
     * Ends the game, calls the calculate scores method, and returns an arrayList of the ranking
     * Goes through tie breaking conditions. Should we make a separate method for tiebreaking?
     */
    public ArrayList<Player> endGame()
    {
        return null;
    }

}
