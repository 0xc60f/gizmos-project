package Classes;

/**
 * A class which represents a Victory Point earned through a build. These are cards in the actual game.
 */
public class BonusVictoryPoint {
    private final int value;

    /**
     * Creates a Victory Point with the given value.
     * @param v The value of the Victory Point.
     */

    public BonusVictoryPoint(int v)
    {
        value = v;
    }

    /**
     * Gets the value of the Victory Point.
     * @return The value of the Victory Point.
     */
    public int getValue()
    {
        return value;
    }
}
