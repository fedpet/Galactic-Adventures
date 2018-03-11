package it.unibo.oop17.ga_game.model;

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
    public void update(final double dt) {
        getEntity().getBody().setLinearVelocity(getDesiredMovement());
    }

}
