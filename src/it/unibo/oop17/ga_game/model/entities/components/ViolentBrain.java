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
     * Checks if we're touching an hated entity. In this case we attack it!.
     */
    @Override
    public void update(final double dt) {
        super.update(dt);
        getEntity().getBody().getContacts().forEach(this::handleContact);
    }

    /**
     * Attack!
     * 
     * @param other
     *            The other @EntityBody.
     */
    private void handleContact(final EntityBody other) {
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
