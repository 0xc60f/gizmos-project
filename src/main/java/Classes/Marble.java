package Classes;

/**
 * A class which represents a marble. This is used to hold the color of the marble.
 * @author Joey
 */
public class Marble {
    private MarbleColor originalColor;
    private MarbleColor newColor;

    /**
     * Creates a marble with the given color, which is a <code>MarbleColor</code> enum.
     * @param color The color of the marble.
     */
    public Marble(MarbleColor color) {
        originalColor = color;
    }

    /**
     * Sets the color of the marble, which is a <code>MarbleColor</code> enum.
     * @param color The color of the marble.
     */
    public void setOldColor(MarbleColor color) {
        originalColor = color;
    }

    /**
     * Gets the color of the marble, which is a <code>MarbleColor</code> enum.
     * @return The color of the marble.
     */
    public MarbleColor getOldColor() {
        return originalColor;
    }

    public MarbleColor getNewColor()
    {
        return newColor;
    }

    public void setNewColor(MarbleColor color) {
        newColor = color;
    }


}
