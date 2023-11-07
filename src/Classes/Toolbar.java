package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

/**
 * A class which represents a Toolbar in the game. This is a collection of GizmoCards which the player either has built or is ready to build.
 * @author 0xc60f
 */
public class Toolbar {
    private TreeMap<GizmoType, ArrayList<GizmoCard>> cards;
    int xCoord, yCoord;

    /**
     * Gets the cards in the Toolbar.
     * @return The cards in the Toolbar as a <code>TreeMap</code>.
     */
    public TreeMap<GizmoType, ArrayList<GizmoCard>> getCards() {
        return cards;
    }
    /**
     * Initializes a new Toolbar.
     */
    public Toolbar(){
        cards = new TreeMap<>();
        Arrays.stream(GizmoType.values()).forEach(type -> cards.put(type, new ArrayList<>()));
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * Adds a GizmoCard to the Toolbar in the corresponding section.
     * @param card The GizmoCard to add.
     */
    public void addGizmoCard(GizmoCard card){
        if(cards.containsKey(card.getType())){
            cards.get(card.getType()).add(card);
        }
        else{
            ArrayList<GizmoCard> temp = new ArrayList<>();
            temp.add(card);
            cards.put(card.getType(), temp);
        }
    }
}
