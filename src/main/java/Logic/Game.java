package Logic;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import Classes.*;

public class Game
{
    private final ArrayList<Player> playerList;
    private final Deck deck;
    private Player currentPlayer;
    private final Player firstPlayer;
    private final boolean gameEnd;
    private final boolean gameStart;
    private final EnergyDispenser energyDispenser;

    /**
     * instantiates all attributes related to the game
     */
    public Game()
    {
        playerList = new ArrayList<>();
        IntStream.rangeClosed(1, 4).forEach(i -> playerList.add(new Player(i)));
        try {
            deck = new Deck();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        currentPlayer = playerList.get(0); //not sure of
        firstPlayer = playerList.get(0);
        gameEnd = false;
        gameStart = true;
        energyDispenser = new EnergyDispenser();

    }

    /**
     * runs the player's turn
     */
    public void play(Player p)
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
    public void setNextPlayer()
    {
        currentPlayer = playerList.get((playerList.indexOf(currentPlayer) + 1) % playerList.size());
    }

    /**
     * What happens after the game ends, calls the calculate scores method, and returns an arrayList of the ranking, and tiebreakers
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
        //Check all players and their cards. Return true if any player has at least four cards where the GizmoLevel is LEVEL3
        for (Player p : playerList) {
            Toolbar playerToolbar = p.getToolBar();
            //The getCards method returns a treemap of arraylists of gizmocards. Use streams to accumulate all the values in all of the arraylists into one arraylist
            ArrayList<GizmoCard> allCards = playerToolbar.getCards().values().stream().flatMap(Collection::stream).collect(Collectors.toCollection(ArrayList::new));
            if (allCards.size() >= 16) return true;
        }
        if (deck.isDeckEmpty() || energyDispenser.isEmpty()) return true;
        for (Player p : playerList) {
            Toolbar playerToolbar = p.getToolBar();
            ArrayList<GizmoCard> allCards = new ArrayList<>();
            for (ArrayList<GizmoCard> gizmoCards : playerToolbar.getCards().values()) {
                allCards.addAll(gizmoCards);
            }
            int numlevel3 = (int) allCards.stream().filter(c -> c.getLevel().equals(GizmoLevel.LEVEL3)).count();
            if (numlevel3 >= 4) return true;
        }
        return false;
    }

}
