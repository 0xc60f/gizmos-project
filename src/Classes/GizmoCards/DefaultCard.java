package Classes.GizmoCards;

import Classes.*;
import java.util.HashMap;

/**
 * The first card that every player gets when they start the game. This is a file card, which means that it is a card that allows for picking.
 * @author 0xc60f
 */
public class DefaultCard extends GizmoCard {
    @Override
    public GizmoType getType() {
        return GizmoType.FILE;
    }

    public DefaultCard() {
        super();
    }

    @Override
    public GizmoLevel getLevel() {
        return super.getLevel();
    }

    @Override
    public void gizmoEffect() {
        super.gizmoEffect();
    }

    @Override
    public boolean trigger() {
        return super.trigger();
    }

    @Override
    public void activate() {
        super.activate();
    }
}
