package Logic;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.HashSet;
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
        for (int i = 1; i <= 4; i++)
            playerList.add(new Player(i));
        deck = new Deck();
        currentPlayer = playerList.get(0); //not sure of
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

    public int determineFirstPlayer()
    {

    }

    /**
     * returns a list of all players' scores in corresponding order
     */
    public ArrayList<Integer> calculateScores()
    {

    }

    public int determinePlayerOrder()
    {

    }

    /**
     * returns the currentPlayer
     */
    public Player getCurrentPlayer()
    {

    }

    /**
     * Ends the game, calls the calculate scores method, and returns an arrayList of the ranking
     * Goes through tie breaking conditions. Should we make a separate method for tiebreaking?
     */
    public ArrayList<Player> endGame()
    {

    }

}
