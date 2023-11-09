package Classes;

/**
 * An interface which holds the basic structure of a card. This is implemented by all <code>GizmoCard</code> implementations.
 * @author 0xc60f
 */
public interface Card {
    /**
     * Gets the level of the card. This should be one of the values in <code>GizmoLevel</code> when instantiated..
     * @implNote This is used to determine the level of the gizmo to be built.
     * @return The level of the card as a <code>GizmoLevel</code> enum.
     */
    public GizmoLevel getLevel();
    /**
     * Gets the type of the card. This should be one of the values in <code>GizmoType</code> when instantiated.
     * @implNote This is used to determine the type of the gizmo to be built.
     * @return The type of the card as a <code>GizmoType</code> enum.
     */
    public GizmoType getType();

    /**
     * Defines the Gizmo effect that the card has. This is called when the card is activated by the player.
     * @implSpec This should be implemented by all <code>GizmoCard</code> implementations and should define the actions taken step-by-step.
     * @implNote This is used to determine the effect of the gizmo to be built.
     */
    public void gizmoEffect();
}
