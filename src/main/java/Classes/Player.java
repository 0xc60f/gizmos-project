package Classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
public class Player implements Comparable<Player>
{
    ArrayList<BonusVictoryPoint> victoryPoints;
    ArrayList<Marble> energyRing;
    ArrayList<GizmoCard> archive;
    Toolbar toolbar;
    boolean fileBlocked, researchBlocked;
    int playerNumber, numberToResearch, maxEnergy, score, maxArchive;

    /**
     * Instantiates all the player's attributes
     * Sets the starting sizes for ring size, archive size, and research size
     * Sets score to 0 and player can file and research
     */
    public Player(int num)
    {
        victoryPoints = new ArrayList<>();
        energyRing = new ArrayList<>();
        archive = new ArrayList<>();
        toolbar = new Toolbar();
        fileBlocked = false;
        researchBlocked = false;
        playerNumber = num;
        numberToResearch = 3;
        maxEnergy = 5;
        maxArchive = 1;
        score = 0;
    }


    public int getPlayerNumber()
    {
        return playerNumber;
    }
    /**
     * Takes in the three categories to upgrade and calls the toolbar method to upgrade each one at a time
     * The toolbar method will add the parameter to the player's category's size
     * Only upgrades the category if it is greater than 0
     *
     * @param type The type of upgrade as an <code></code>
     */
    public void upgrade(UpgradeType type, int num)
    {
        switch (type)
        {
            case RING:
                if (num > 0)
                    maxEnergy += num;
                break;
            case ARCHIVE:
                if (num > 0)
                    maxArchive += num;
                break;
            case RESEARCH:
                if (num > 0)
                    numberToResearch += num;
                break;
        }
    }

    /**
     * Turns a marble the player has in their energy ring into another color
     * @param oldColor	color of marble that is being converted
     * @param newColor	color that the marble is being converted into
     */
    public void convert1To1(MarbleColor oldColor, MarbleColor newColor)
    {
        for (Marble m : energyRing) {
            if (m.getColor() == oldColor) {
                m.setColor(newColor);
                break;
            }
        }
    }

    /**
     * Turns a marble the player has in their energy ring into 2 marbles of a different color
     * @param oldColor	color of marble that is being converted
     * @param newColor	color that the marble is being converted into
     */
    public void convert1To2(MarbleColor oldColor, MarbleColor newColor)
    {
        for (Marble m : energyRing) {
            if (m.getColor() == oldColor) {
                m.setColor(newColor);
                energyRing.add(m);
                break;
            }
        }
    }

    /**
     * checks if a player can add a card to their archive and if so, calls the toolbar method to add it
     * @param g card being added to player's archive
     */
    public void file(GizmoCard g)
    {
        if (archive.size() < maxArchive)
            toolbar.addGizmoCard(g);
    }

    /**
     * Checks if player as an available space in their energy ring. If so returns an arrayList of the edited first 6
     * that we will set to the first6 in the energyDispenser class
     * If not, then it returns the same first6 that has not been edited
     * Takes in the player's choice and removes it from the first 6 to add to their energyRing
     *
     * @param choice player's pick from the first 6 marbles
     * @param first6 the first 6 marbles of the energyDispenser
     */
    public ArrayList<Marble> pickFrom6(int choice, ArrayList<Marble> first6)
    {
        if (energyRing.size() < maxEnergy)
        {
            energyRing.add(first6.remove(choice-1));
            return first6;
        }
        return first6;
    }

    /**
     * Checks if player has space in their energyRing and returns an arrayList of the energyDispenser that has been edited.
     * We will set it back to the energyDispenser in the energyDispenser class
     * Removes a random marble from the energyDispenser and adds it to the player's energyRing
     * @param energyDispenser	copy of the energyDispenser in the energyDispenser class
     */
    public ArrayList<Marble> pickRandom(ArrayList<Marble> energyDispenser)
    {
        if (energyRing.size() < maxEnergy)
        {
            int size = energyDispenser.size();
            Random rand = new Random();
            int randomChoice = rand.nextInt(size);
            energyRing.add(energyDispenser.remove(randomChoice));
            return energyDispenser;
        }
        return energyDispenser;
    }


    /**
     * Checks if player has enough energy to build the gizmo and if so calls the toolbar method addGizmo();
     * @param numOfRed			number of red energy needed to build the gizmo
     * @param numOfBlue			number of blue energy needed to build the gizmo
     * @param numOfYellow		number of yellow energy needed to build the gizmo
     * @param numOfBlack		number of black energy needed to build the gizmo
     */
    public void build(int numOfRed, int numOfBlue, int numOfYellow, int numOfBlack, GizmoCard g)
    {
        int red = 0; int blue = 0; int yellow = 0; int black = 0;
        for (Marble m : energyRing) {
            if (m.getColor() == MarbleColor.RED)
                red++;
            else if (m.getColor() == MarbleColor.BLUE)
                blue++;
            else if (m.getColor() == MarbleColor.YELLOW)
                yellow++;
            else if (m.getColor() == MarbleColor.BLACK)
                black++;
        }
        if (red >= numOfRed && blue >= numOfBlue && yellow >= numOfYellow && black >= numOfBlack)
            toolbar.addGizmoCard(g);

    }

    /**
     * returns an arrayList of the cards a player researches
     * @param deckTier	the deck the player is researching from
     */
    public ArrayList<GizmoCard> research(ArrayList<GizmoCard> deckTier)
    {
        ArrayList<GizmoCard> cardsToDraw = new ArrayList<GizmoCard>();
        for (int i = 0; i < numberToResearch; i++)
            cardsToDraw.add(deckTier.remove(0));
        return cardsToDraw;
    }


    /**
     * returns the limit of the player's ring
     */
    public int getEnergyRingMax()
    {
        return maxEnergy;
    }

    /**
     * returns the limit of the player's archive
     */
    public int getArchiveMax()
    {
        return numberToResearch;
    }

    /**
     * stops the player from filing
     */
    public void blockFile()
    {
        fileBlocked = true;
    }

    /**
     * stops the player from researching
     */
    public void blockResearch()
    {
        researchBlocked = true;
    }

    /**
     * returns the player's score
     */
    public int getScore()
    {
        return score;
    }

    /**
     * adds a specified amount of victory points to the score
     */
    public void addToScore(int x)
    {
        score += x;
    }

    /**
     * returns a HashSet of all available actions that the player can activate
     */
    public HashSet<GizmoCard> checkForAvailableActions()
    {
        return new HashSet<>();
    }

    /**
     * returns the player's toolbar
     */
    public Toolbar getToolBar()
    {
        return toolbar;
    }

    /**
     * Goes through the player's bonus victory points and adds up and returns all the points
     */
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

    /**
     * Gets the player's energy ring.
     * @return The player's energy ring as an <code>ArrayList</code> of <code>Marble</code>s.
     */
    public ArrayList<Marble> getEnergyRing() {
        return energyRing;
    }
}
