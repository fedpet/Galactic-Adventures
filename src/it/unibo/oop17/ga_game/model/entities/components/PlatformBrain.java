package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.utils.FXUtils;
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
    private void takeCareOfPassenger(final EntityBody passenger) {
        final Point2D myVelocity = getEntity().getBody().getLinearVelocity();
        Point2D correction = myVelocity.subtract(passenger.getLinearVelocity());
        if (correction.getY() > 0) {
            // if we're going up we don't need to also push passengers up.
            // gravity will do its work.
            correction = new Point2D(correction.getX(), 0);
        }
        // cap the correction so we don't prevent passenger movement.
        correction = FXUtils.absCap(correction, myVelocity.getX(), myVelocity.getY());
        passenger.applyImpulse(correction);
    }
}
