package it.unibo.oop17.ga_game.model.entities.components;

import java.util.function.Supplier;

import it.unibo.oop17.ga_game.model.physics.BodyContact;
import javafx.geometry.Point2D;

/**
 * A PlatformBrain follows a pattern and takes care of passengers safety.
 */
public final class PlatformBrain extends FixedPatternBrain {
    /**
     * 
     * @param nextPositionSupplier A Supplier for the positions to follow.
     */
    public PlatformBrain(final Supplier<Point2D> nextPositionSupplier) {
        super(nextPositionSupplier);
    }

    @Override
    public void update(final double dt) {
        super.update(dt);
        takeCareOfPassengers();
    }

    @Override
    public Personality getPersonality() {
        return EntityPersonality.NONE;
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
                .filter(c -> c.getPoint().getY() <= -getEntity().getBody().getDimension().getHeight() / 2)
                .map(BodyContact::getOtherBody)
                .forEach(passenger -> {
                    if (passenger.getLinearVelocity().getY() <= 0) {
                        passenger.applyImpulse(correction);
                    }
        });
    }
}
