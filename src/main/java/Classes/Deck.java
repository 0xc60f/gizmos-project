package Classes;
import java.util.*;
import java.util.stream.IntStream;

/**
 * A class which represents a deck of cards. This is a singleton class, as there should only be one deck of cards in the game.
 * @author 0xc60f
 */
public class Deck {
    private EnumMap<GizmoLevel, Stack<GizmoCard>> cards;
    private GizmoCard[] tier1;
    private GizmoCard[] tier2;
    private GizmoCard[] tier3;

    /**
     * Initializes the deck of cards. This should only be called once.
     * This will create the decks of cards and move the top 5 cards from each deck into the tier arrays.
     */
    public Deck() {
        cards = new EnumMap<>(GizmoLevel.class);
        for (GizmoLevel level : GizmoLevel.values()) {
            cards.put(level, new Stack<>());
            Collections.shuffle(cards.get(level));
        }
        tier1 = new GizmoCard[4];
        tier2 = new GizmoCard[3];
        tier3 = new GizmoCard[2];
        IntStream.range(0, 4).forEach(i -> tier1[i] = cards.get(GizmoLevel.LEVEL1).pop());
        IntStream.range(0, 3).forEach(i -> tier2[i] = cards.get(GizmoLevel.LEVEL2).pop());
        IntStream.range(0, 2).forEach(i -> tier3[i] = cards.get(GizmoLevel.LEVEL3).pop());

    }

    /**
     * Gets the face-up cards for the given level.
     * @param level The level to get the face-up cards for as a <code>GizmoLevel</code> enum.
     * @return The face-up cards for the given level as an array of <code>GizmoCard</code>s.
     */
    public GizmoCard[] getFaceUp(GizmoLevel level) {
        return switch (level) {
            case LEVEL1 -> tier1;
            case LEVEL2 -> tier2;
            case LEVEL3 -> tier3;
            default -> null;
        };
    }

    /**
     * Puts the given card on the bottom of the deck for the given level.
     * @param card The card to put on the bottom of the deck as a <code>GizmoCard</code>.
     * @param level The level to put the card on the bottom of the deck for as a <code>GizmoLevel</code> enum.
     */
    public void addToBottom(GizmoCard card, GizmoLevel level) {
        cards.get(level).add(0, card);
    }

    /**
     * Gets the top card from the deck for the given level.
     * @param level The level to get the top card from as a <code>GizmoLevel</code> enum.
     * @return The top card from the deck for the given level as a <code>GizmoCard</code>.
     */
    public GizmoCard drawGizmo(GizmoLevel level) {
        return cards.get(level).pop();
    }

    /**
     * Researches the given amount of cards for the given level.
     * @param level The level to research cards for as a <code>GizmoLevel</code> enum.
     * @param amount The amount of cards to research as an <code>int</code>.
     * @return The researched cards as an <code>ArrayList</code> of <code>GizmoCard</code>s.
     */
    public ArrayList<GizmoCard> research(GizmoLevel level, int amount) {
        ArrayList<GizmoCard> cardList = new ArrayList<>();
        IntStream.range(0, amount).forEach(i -> cards.get(level).add(cards.get(level).pop()));
        return cardList;
    }

}
