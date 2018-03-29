package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * A PlatformBrain takes care of passengers safety.
 */
public final class PlatformBrain extends AbstractBrain {

    /**
     * 
     */
    public PlatformBrain() {
        super(EntityPersonality.NONE);
    }

    @Override
    protected void handleContact(final EntityBody other) {
        if (PositionCompare.relativeSide(getEntity().getBody(), other) == Side.TOP) {
            takeCareOfPassenger(other);
        }
    }

    /**
     * adjust passengers velocity so they don't fall of the platform.
     * 
     * @param passenger
     *            The @EntityBody sitting on the platform
     */
    protected void takeCareOfPassenger(final EntityBody passenger) {
        Point2D velocity = getEntity().getBody().getLinearVelocity();
        if (velocity.getY() > 0) {
            // if we're going up we don't need to also push passengers up.
            velocity = new Point2D(velocity.getX(), 0);
        }
        final Point2D correction = velocity;
        if (passenger.getLinearVelocity().getY() <= 0) {
            passenger.applyImpulse(correction);
        }
    }
}
