package Classes;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Iterator;

public class Player implements Comparable<Player>
{
    ArrayList<BonusVictoryPoint> victoryPoints;
    ArrayList<Marble> energyRing;
    ArrayList<GizmoCard> archive;
    Toolbar toolbar;
    boolean fileBlocked, researchBlocked;
    int playerNumber, numberToResearch, maxEnergy, score;

    public Player(int num)
    {
        victoryPoints = new ArrayList<BonusVictoryPoint>();
        energyRing = new ArrayList<Marble>();
        archive = new ArrayList<GizmoCard>();
        toolbar = new Toolbar();
        fileBlocked = false;
        researchBlocked = false;
        playerNumber = num;
        numberToResearch = 3;
        maxEnergy = 5;
        score = 0;
    }

    public void upgrade()
    {

    }

    public void convert()
    {

    }

    public void file()
    {

    }

    public void pickFrom6()
    {

    }

    public void pickRandom()
    {

    }

    public void build()
    {

    }

    public void research()
    {

    }

    public int getEnergyRingMax()
    {
        return maxEnergy;
    }

    public int getArchiveMax()
    {
        return numberToResearch;
    }

    public void blockFile()
    {
        fileBlocked = true;
    }

    public void blockResearch()
    {
        researchBlocked = true;
    }

    public int getScore()
    {
        return score;
    }

    public void addToScore(int x)
    {
        score += x;
    }

    public HashSet<GizmoCard> checkForAvailableActions()
    {

    }

    public Toolbar getToolBar()
    {
        return toolbar;
    }

    public int getNumBonusVicPoints()
    {
        return victoryPoints.stream().mapToInt(BonusVictoryPoint::getValue).sum();
    }

    /**
     * Compares this player to another player.
     * @param o The player to compare to.
     * @return A negative number if this player's turn is before the other player's turn, a positive number if this player's turn is after the other player's turn, and 0 if they are the same.
     */
    @Override
    public int compareTo(Player o) {
        return playerNumber - o.playerNumber;
    }
}
