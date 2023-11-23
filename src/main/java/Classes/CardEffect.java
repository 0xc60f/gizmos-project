package Classes;

/**
 * An interface which holds the decision-making structure of a card. This is implemented by all <code>GizmoCard</code> implementations.
 * @author 0xc60f
 */
public interface CardEffect {
    /**
     * Defines what needs to be done to trigger the card. This is called when the card is activated by the player.
     * @implSpec This should be implemented by all <code>GizmoCard</code> implementations and should check all necessary conditions.
     * @return True if the card can be activated, false otherwise.
     */
    boolean trigger();

    /**
     * Activates the card. This is called when the card is activated by the player.
     * @implSpec This should be implemented by all <code>GizmoCard</code> implementations and should define the actions taken step-by-step. This should work with the <code>Card</code> interface
     * to define how a Gizmo's effect is activated.
     */
    void activate();
}
