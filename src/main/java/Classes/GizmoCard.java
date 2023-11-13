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
    private int imageNumber, effectNumber;
    private int numOfVictoryPts;
    private String category, effectType;

    /**
     * Creates a GizmoCard with the given cost.
     * @param typeMarble The type of Marble that the GizmoCard needs to be built.
     * @param cost The number of marbles needed to build the GizmoCard.
     * @param numOfVictoryPts the amount of victory points the card is worth
     * @param category what tab the card belongs under. Ex: upgrade, pick, file
     * @param effectType what kind of effect the card has. Ex: build, buildFrom file, file, upgrade
     * @param effectNum the method number in that effectTypeClass
     * @param image number of the image in our folder of all images starting from 1
     */
    public GizmoCard(Marble typeMarble, int cost, int numOfVictoryPts, String category, String effectType, int effectNum, int image) {
        this.cost = new HashMap<>();
        this.cost.put(typeMarble, cost);
        this.numOfVictoryPts = numOfVictoryPts;
        this.category = category;
        this.effectType = effectType;
        effectNumber = effectNum;
        imageNumber = image;
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
    public int getImage() {
        return imageNumber;
    }

    /**
     * Sets the image of the GizmoCard.
     * @param image The image of the GizmoCard as a <code>BufferedImage</code>.
     */
    public void setImage(int image) {
        imageNumber = image;
    }

    public HashMap<Marble, Integer> getCost() {
        return cost;
    }


}
