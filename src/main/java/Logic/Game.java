package Logic;

import java.util.*;
import java.util.stream.IntStream;

import Classes.*;

public class Game
{
    private ArrayList<Player> playerList;
    private Deck deck;
    private Player currentPlayer;
    private Player firstPlayer;
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
        currentPlayer = playerList.get(0); //not sure of
        firstPlayer = playerList.get(0);
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
        return (currentPlayerNum + 1) % 4;
    }

    /**
     * What happens after the game ends, calls the calculate scores method, and returns an arrayList of the ranking
     */
    public ArrayList<Player> endGame()
    {
        ArrayList<Player> ranking = new ArrayList<>(playerList);
        ranking.sort(Comparator.comparing(Player::getScore).reversed());
        //If any players have the same two scores, sort only those players by their number of cards
        for (int i = 0; i < ranking.size() - 1; i++)
        {
            if (ranking.get(i).getScore() == ranking.get(i + 1).getScore())
            {
                int personOneCards = ranking.get(i).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                int personTwoCards = ranking.get(i + 1).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                if (personOneCards < personTwoCards)
                {
                    Collections.swap(ranking, i, i + 1);
                }
            }
        }
        //If any players have the same score and number of cards, sort only those players by the number of marbles in their reserve
        for (int i = 0; i < ranking.size() - 1; i++)
        {
            if (ranking.get(i).getScore() == ranking.get(i + 1).getScore())
            {
                int personOneCards = ranking.get(i).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                int personTwoCards = ranking.get(i + 1).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                if (personOneCards == personTwoCards)
                {
                    int personOneMarbles = ranking.get(i).getEnergyRing().size();
                    int personTwoMarbles = ranking.get(i + 1).getEnergyRing().size();
                    if (personOneMarbles < personTwoMarbles)
                    {
                        Collections.swap(ranking, i, i + 1);
                    }
                }
            }
        }
        //If finally, any players are still tied, compare only those players using the compareTo method in the Player class. THe greater number is ranked above
        for (int i = 0; i < ranking.size() - 1; i++)
        {
            if (ranking.get(i).getScore() == ranking.get(i + 1).getScore())
            {
                int personOneCards = ranking.get(i).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                int personTwoCards = ranking.get(i + 1).getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum();
                if (personOneCards == personTwoCards)
                {
                    int personOneMarbles = ranking.get(i).getEnergyRing().size();
                    int personTwoMarbles = ranking.get(i + 1).getEnergyRing().size();
                    if (personOneMarbles == personTwoMarbles)
                    {
                        if (ranking.get(i).compareTo(ranking.get(i + 1)) < 0)
                        {
                            Collections.swap(ranking, i, i + 1);
                        }
                    }
                }
            }
        }
        return ranking;
    }

    /**
     * checks to see if the game will end
     */
    public boolean endGameConditions()
    {
        //Check all players and their cards. Return true if any player has at least fourc ards where the GizmoLevel is LEVEL3
        if (playerList.stream().anyMatch(player -> player.getToolBar().getCards().values().stream().filter(cardList -> cardList.size() >= 4).anyMatch(cardList -> cardList.get(0).getLevel() == GizmoLevel.LEVEL3)))
            return true;
        if (deck.isDeckEmpty()) return true;
        if (energyDispenser.isEmpty()) return true;
        return playerList.stream().anyMatch(player -> player.getToolBar().getCards().values().stream().mapToInt(ArrayList::size).sum() >= 16);

    }

}
