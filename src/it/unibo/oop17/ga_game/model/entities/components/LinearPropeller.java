package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.EventfullEntity;
import javafx.geometry.Point2D;

/**
 * This propeller works without inertia nor friction.
 */
public final class LinearPropeller extends PropellerComponent {

    /**
     * 
     * @param speed
     *            The speed of the propeller.
     */
    public LinearPropeller(final double speed) {
        super(speed);
    }

    /**
     * Removes gravity effects from the owner.
     */
    @Override
    public void attach(final EventfullEntity owner) throws IllegalStateException {
        super.attach(owner);
        owner.getBody().setGravityScale(0);
    }

    @Override
    protected Point2D computeMovement(final double dt) {
        return getDesiredMovement().multiply(getSpeed());
    }

    @Override
    protected void applyMovement(final EntityBody body, final Point2D force) {
        body.setLinearVelocity(getDesiredMovement());
    }
}
