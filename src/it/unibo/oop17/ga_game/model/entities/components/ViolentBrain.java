package it.unibo.oop17.ga_game.model.entities.components;

import javafx.geometry.Point2D;

/**
 * A @Brain which attacks hated entities during contacts.
 * It just tries to use its weapon as if it was a melee one.
 */
public class ViolentBrain extends AbstractBrain {
    /**
     * @param personality
     *            Brain @Personality
     */
    public ViolentBrain(final Personality personality) {
        super(personality);
    }

    /**
     * Attack!
     * 
     * @param other
     *            The other @EntityBody.
     */
    @Override
    protected void handleContact(final EntityBody other) {
        other.getOwner().ifPresent(otherEntity -> {
            if (hate(otherEntity)) {
                getEntity().get(Weapon.class).ifPresent(weapon -> {
                    weapon.use(relativePosition(otherEntity.getBody()));
                });
            }
        });
    }

    private Point2D relativePosition(final EntityBody other) {
        return getEntity().getBody().getPosition().subtract(other.getPosition());
    }
}
