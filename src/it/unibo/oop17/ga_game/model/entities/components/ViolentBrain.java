package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.Entity;
import javafx.geometry.Point2D;

/**
 * A {@link Brain} component which attacks hated entities during contacts.
 * It just tries to use its weapon as if it was a melee one.
 */
public class ViolentBrain extends AbstractBrain {
    /**
     * @param personality
     *            Brain personality
     */
    public ViolentBrain(final Personality personality) {
        super(personality);
    }

    /**
     * Attack!
     * 
     * @param other
     *            The other {@link EntityBody} object.
     */
    @Override
    protected void handleContact(final EntityBody other) {
        other.getOwner().ifPresent(otherEntity -> {
            if (hate(otherEntity)) {
                attack(otherEntity);
            }
        });
    }

    private void attack(final Entity other) {
        getEntity().get(Weapon.class).ifPresent(weapon -> {
            weapon.use(relativePosition(other.getBody()));
        });
    }

    private Point2D relativePosition(final EntityBody other) {
        return getEntity().getBody().getPosition().subtract(other.getPosition());
    }
}
