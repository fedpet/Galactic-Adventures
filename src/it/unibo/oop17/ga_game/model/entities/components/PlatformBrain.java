package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * A PlatformBrain follows a pattern and takes care of passengers safety.
 */
public final class PlatformBrain extends AbstractBrain {

    /**
     * 
     */
    public PlatformBrain() {
        super(EntityPersonality.NONE);
    }

    @Override
    public void update(final double dt) {
        super.update(dt);
        takeCareOfPassengers();
    }

    /**
     * adjust passengers velocity so they don't fall of the platform.
     */
    protected void takeCareOfPassengers() {
        Point2D velocity = getEntity().getBody().getLinearVelocity();
        if (velocity.getY() > 0) {
            // if we're going up we don't need to also push passengers up.
            velocity = new Point2D(velocity.getX(), 0);
        }
        final Point2D correction = velocity;
        getEntity().getBody().getContacts()
                .filter(body -> PositionCompare.relativeSide(getEntity().getBody(), body) == Side.TOP)
                .forEach(passenger -> {
                    if (passenger.getLinearVelocity().getY() <= 0) {
                        passenger.applyImpulse(correction);
                    }
        });
    }
}
