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
//    private final HashMap<MarbleColor, Integer> cost;
    private MarbleColor colorOfCost;
    private int cost;
    private int effectNumber;
    private int numOfVictoryPts;
    private String effectType;
    private GizmoType category;
    private GizmoLevel level;
    private BufferedImage image;
    private MarbleColor color1;
    private MarbleColor color2;
    private boolean triggered;

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
    public GizmoCard(MarbleColor typeMarble, int cost, int numOfVictoryPts, GizmoType category, String effectType, int effectNum, BufferedImage image, GizmoLevel level) {
        colorOfCost = typeMarble;
        this.cost = cost;
        this.numOfVictoryPts = numOfVictoryPts;
        this.category = category;
        this.effectType = effectType;
        effectNumber = effectNum;
        this.image = image;
        this.level = level;
        triggered = false;
    }
    public GizmoCard(MarbleColor typeMarble, int cost, int numOfVictoryPts, GizmoType category, String effectType, int effectNum, BufferedImage image, MarbleColor color1, GizmoLevel level) {
        colorOfCost = typeMarble;
        this.cost = cost;
        this.numOfVictoryPts = numOfVictoryPts;
        this.category = category;
        this.effectType = effectType;
        effectNumber = effectNum;
        this.image = image;
        this.color1 = color1;
        this.level = level;
        triggered = false;
    }
    public GizmoCard(MarbleColor typeMarble, int cost, int numOfVictoryPts, GizmoType category, String effectType, int effectNum, BufferedImage image, MarbleColor color1, MarbleColor color2, GizmoLevel level) {
        colorOfCost = typeMarble;
        this.cost = cost;
        this.numOfVictoryPts = numOfVictoryPts;
        this.category = category;
        this.effectType = effectType;
        effectNumber = effectNum;
        this.image = image;
        this.color1 = color1;
        this.color2 = color2;
        this.level = level;
        triggered = false;
    }

    /**
     * Default constructor for a GizmoCard. Only use this for <code>DefaultCard</code>.
     */

    public GizmoCard() {
        cost = 0;
    }

    @Override
    public GizmoLevel getLevel() {
        return level;
    }

    @Override
    public GizmoType getType() {
        return category;
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

    public MarbleColor getColor1() {
        return color1;
    }

    public MarbleColor getColor2() {
        return color2;
    }
    public boolean isTriggered() {
        return triggered;
    }
    public void setTriggered(boolean triggered) {
        this.triggered = triggered;
    }
    public int getEffectNumber() {
        return effectNumber;
    }

    public int getNumOfVictoryPts() {
        return numOfVictoryPts;
    }
    public GizmoType getCategory() {
        return category;
    }
    public GizmoLevel getGizmoLevel() {
        return level;
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

    public int getCost() {
        return cost;
    }

    public MarbleColor getColorOfCost() {
        return colorOfCost;
    }

//    public String toString()
//    {
//        return "Type of card: "+category +"   Cost of card: "+cost;
//    }


}
