package Classes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Holds all marbles that are used in the game. This is used to dispense marbles to the players and to hold marbles used to build Gizmos.
 * @see MarbleColor
 * @author Joey (with edits by 0xc60f)
 */
public class EnergyDispenser
{
    private ArrayList<Marble> marbles;
    private int xCoord, yCoord;

    /**
     * Creates a new <code>EnergyDispenser</code> object. This is used to dispense marbles to the players and to hold marbles used to build Gizmos.
     * This adds the required amount of marbles to the dispenser as defined in the game rules.
     * @see MarbleColor
     */
    public EnergyDispenser()
    {
        marbles = new ArrayList<>();
        addAllMarbles();
    }

    public ArrayList<Marble> getFirstSix()
    {
        return IntStream.range(0, 6).<Marble>mapToObj(i -> marbles.get(i)).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Gets a random marble from the dispenser and removes it from the dispenser. This is used for when a player is picking a marble from the top of the dispenser.
     * @see Player
     * @see Marble
     * @return A random marble from the dispenser.
     */
    public Marble getRandMarble()
    {
        return marbles.remove((int) (Math.random() * marbles.size()));
    }

    /**
     * Adds a marble to the dispenser and shuffles the marbles in the dispenser.
     * @param m The marble to add to the dispenser.
     */
    public void addMarble(Marble m)
    {
        marbles.add(m);
        Collections.shuffle(marbles);
    }

    /**
     * Adds 14 marbles of each color to the dispenser and shuffles them.
     * This is called in the constructor at the start of the game.
     */
    private void addAllMarbles()
    {
        IntStream.range(0, 14).forEach(i -> {
            marbles.add(new Marble(MarbleColor.BLUE));
            marbles.add(new Marble(MarbleColor.RED));
            marbles.add(new Marble(MarbleColor.YELLOW));
            marbles.add(new Marble(MarbleColor.BLACK));
        });
        Collections.shuffle(marbles);
    }

    /**
     * Gets the x coordinate of the dispenser. This is the top left corner of the dispenser.
     * @return The x coordinate of the dispenser as an int.
     */

    public int getXCoord()
    {
        return xCoord;
    }

    /**
     * Sets the x coordinate of the dispenser. This is the top left corner of the dispenser.
     * @param x The x coordinate of where the dispenser should be drawn as an int.
     */

    public void setXCoord(int x)
    {
        xCoord = x;
    }

    /**
     * Gets the y coordinate of the dispenser. This is the top left corner of the dispenser.
     * @return The y coordinate of the dispenser as an int.
     */
    public int getyCoord()
    {
        return yCoord;
    }

    /**
     * Sets the y coordinate of the dispenser. This is the top left corner of the dispenser.
     * @param y The y coordinate of where the dispenser should be drawn as an int.
     */

    public void setyCoord(int y)
    {
        yCoord = y;
    }

}
