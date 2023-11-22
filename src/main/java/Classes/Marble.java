package Classes;

/**
 * A class which represents a marble. This is used to hold the color of the marble.
 * @author Joey
 */
public class Marble {
    private MarbleColor color;

    /**
     * Creates a marble with the given color, which is a <code>MarbleColor</code> enum.
     * @param color The color of the marble.
     */
    public Marble(MarbleColor color) {
        this.color = color;
    }

    /**
     * Sets the color of the marble, which is a <code>MarbleColor</code> enum.
     * @param color The color of the marble.
     */
    public void setColor(MarbleColor color) {
        this.color = color;
    }

    /**
     * Gets the color of the marble, which is a <code>MarbleColor</code> enum.
     * @return The color of the marble.
     */
    public MarbleColor getColor() {
        return color;
    }

}
