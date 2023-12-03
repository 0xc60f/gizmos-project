package Classes;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.*;

public class Player implements Comparable<Player>
{
    ArrayList<BonusVictoryPoint> victoryPoints;
    EnergyRing energyRing;
    ArrayList<GizmoCard> archive;
    ArrayList<GizmoCard> cardsResearching;
    Toolbar toolbar;
    boolean fileBlocked, researchBlocked;
    int playerNumber, maxResearch, score, maxArchive, convertMethodChoice, player1Or2ConvertChoice, actionPicked;

    /**
     * Instantiates all the player's attributes
     * Sets the starting sizes for ring size, archive size, and research size
     * Sets score to 0 and player can file and research
     */
    public Player(int num) throws IOException {
        victoryPoints = new ArrayList<BonusVictoryPoint>();
        energyRing = new EnergyRing();
        archive = new ArrayList<GizmoCard>();
        cardsResearching = new ArrayList<GizmoCard>();
        toolbar = new Toolbar();
        toolbar.addGizmoCard(new GizmoCard(MarbleColor.ANY, 0,0, GizmoType.FILE, "filePick1Random", 4, ImageIO.read(Objects.requireNonNull(Player.class.getResource("/Images/GizmoCards/FirstFileCard.png"))), GizmoLevel.LEVEL0));
        fileBlocked = false; researchBlocked = false;
        playerNumber = num;
        maxResearch = 3;
        maxArchive = 1;
        score = 0;
    }


    public ArrayList<GizmoCard> getArchive()
    {
        return archive;
    }

//    public void setArchive(ArrayList<GizmoCard> list)
//    {
//        archive = list;
//    }

    public void setMaxArchive(int x)
    {
        maxArchive = x;
    }
    public ArrayList<GizmoCard> getCardsResearching()
    {
        return cardsResearching;
    }

    public void addCardsResearching(GizmoCard g)
    {
        cardsResearching.add(g);
    }

    public void setCardsResearching(ArrayList<GizmoCard> list)
    {
        cardsResearching = list;
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
                    energyRing.updateEnergyRingMax(num);
                break;
            case ARCHIVE:
                if (num > 0)
                    maxArchive += num;
                break;
            case RESEARCH:
                if (num > 0)
                    maxResearch += num;
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
        for (Marble m : energyRing.getRing()) {
            if (m.getOldColor() == oldColor) {
                m.setOldColor(newColor);
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
        for (Marble m : energyRing.getRing()) {
            if (m.getOldColor() == oldColor) {
                m.setOldColor(newColor);
                energyRing.addMarble(m);
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
//        if (archive.size() < maxArchive)
        archive.add(g);
    }

    /**
     * Checks if player as an available space in their energy ring.
     * Takes in the player's choice and removes it from the first 6 to add to their energyRing
     *
     * @param choice player's pick from the first 6 marbles
     * @param energyDispenser the first 6 marbles of the energyDispenser
     */
    public void pickFrom6(int choice, ArrayList<Marble> energyDispenser)
    {
        energyRing.addMarble(energyDispenser.remove(choice));
    }

    /**
     * Checks if player has space in their energyRing and returns an arrayList of the energyDispenser that has been edited.
     * We will set it back to the energyDispenser in the energyDispenser class
     * Removes a random marble from the energyDispenser and adds it to the player's energyRing
     * @param energyDispenser	copy of the energyDispenser in the energyDispenser class
     */
    public ArrayList<Marble> pickRandom(ArrayList<Marble> energyDispenser)
    {
        if (energyRing.getRing().size() < energyRing.getEnergyRingMax())
        {
            int size = energyDispenser.size();
            Random rand = new Random();
            int randomChoice = rand.nextInt(size);
            energyRing.addMarble(energyDispenser.remove(randomChoice));
            return energyDispenser;
        }
        return energyDispenser;
    }

    /**
     * Checks if player has enough energy to build the gizmo and if so calls the toolbar method addGizmo();
     */
    public void build(GizmoCard g, int cost, MarbleColor color, EnergyDispenser energyDispenser)
    {
        toolbar.addGizmoCard(g);
        for (int i = 0; i < energyRing.getRing().size(); i++)
        {
            if (cost == 0)
                break;
            else if (energyRing.getRing().get(i).getNewColor() == color) {
                energyDispenser.getMarbles().add(energyRing.getRing().remove(i));
                i--;
                cost--;
            }
        }
    }

    /**
     * returns an arrayList of the cards a player researches
     * @param deckTier	the deck the player is researching from
     */
    public ArrayList<GizmoCard> research(ArrayList<GizmoCard> deckTier)
    {
        ArrayList<GizmoCard> cardsToDraw = new ArrayList<GizmoCard>();
        for (int i = 0; i < maxResearch; i++)
            cardsToDraw.add(deckTier.remove(0));
        return cardsToDraw;
    }

    /**
     * returns the limit of the player's archive
     */
    public int getMaxArchive()
    {
        return maxArchive;
    }
    /**
     * returns the limit of the player's archive
     */
    public int getMaxResearch()
    {
        return maxResearch;
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

    public ArrayList<BonusVictoryPoint> getBonusVictoryPoints()
    {
        return victoryPoints;
    }


    public void addBonusVPTokens(BonusVictoryPoint vp)
    {
        victoryPoints.add(vp);
    }

    public void setBonusVP(ArrayList<BonusVictoryPoint> list)
    {
        victoryPoints = list;
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
        return energyRing.getRing();
    }

    public EnergyRing getPlayerRingClass()
    {
        return energyRing;
    }

    public void setActionPicked(int num)
    {
        actionPicked = num;
    }

    /**
     * ACTION PICKED RETURNS INTEGER 1-5
     * 1 = CONVERT
     * 2 = FILE
     * 3 = PICK
     * 4 = BUILD
     * 5 = RESEARCH
     */
    public int getActionPicked()
    {
        return actionPicked;
    }

    public int getConvertMethod()
    {
        return convertMethodChoice;
    }

    public void setConvertMethod(int x)
    {
        convertMethodChoice = x;
    }
    public int get1Or2ConvertChoice()
    {
        return player1Or2ConvertChoice;
    }

    public void set1Or2ConvertChoice(int x)
    {
        player1Or2ConvertChoice = x;
    }

}