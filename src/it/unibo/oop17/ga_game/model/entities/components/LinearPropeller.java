package it.unibo.oop17.ga_game.model.entities.components;

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

    @Override
    protected Point2D computeMovement(final double dt) {
        return getDesiredMovement().multiply(getSpeed());
    }

    @Override
    protected void applyMovement(final EntityBody body, final Point2D force) {
        body.setLinearVelocity(getDesiredMovement());
    }
}
