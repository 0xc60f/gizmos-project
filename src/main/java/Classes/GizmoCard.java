package Classes;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * The default implementation of a GizmoCard. This is a card which represents a Gizmo which the player can build. This is a card in the Toolbar.
 * Every single GizmoCard has a cost, which is a HashMap of Marbles and their respective costs. This is because some Gizmos require multiple Marbles to build.
 * Every card in the game will extend this class, and will have a different cost and effect.
 * @author 0xc60f
 */
public class GizmoCard implements Card, CardEffect {
    private final HashMap<Marble, Integer> cost;
    private BufferedImage image;

    /**
     * Creates a GizmoCard with the given cost.
     * @param typeMarble The type of Marble that the GizmoCard needs to be built.
     * @param cost The number of marbles needed to build the GizmoCard.
     */
    public GizmoCard(Marble typeMarble, int cost, BufferedImage image) {
        this.cost = new HashMap<>();
        this.cost.put(typeMarble, cost);
        this.image = image;
    }

    /**
     * Default constructor for a GizmoCard. Only use this for <code>DefaultCard</code>.
     */

    public GizmoCard() {
        cost = new HashMap<>();
    }

    @Override
    public GizmoLevel getLevel() {
        return null;
    }

    @Override
    public GizmoType getType() {
        return null;
    }

    @Override
    public void gizmoEffect() {

    }

    @Override
    public boolean trigger() {
        return false;
    }

    @Override
    public void activate() {

    }

    /**
     * Gets the image of the GizmoCard.
     * @return The image of the GizmoCard as a <code>BufferedImage</code>.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets the image of the GizmoCard.
     * @param image The image of the GizmoCard as a <code>BufferedImage</code>.
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public HashMap<Marble, Integer> getCost() {
        return cost;
    }
}
