package it.unibo.oop17.ga_game.model;

import javafx.geometry.Point2D;


public class FeetComponent extends AbstractMovementComponent {
    private static final double AIR_FRICTION_FACTOR = 0.07;
    private final double walkingSpeed, jumpingSpeed;

    public FeetComponent(final double walkingSpeed, final double jumpingSpeed) {
        this.walkingSpeed = walkingSpeed;
        this.jumpingSpeed = jumpingSpeed;
    }

    @Override
    public void update(final double dt) {
        Point2D movement = getDesiredMovement().subtract(getEntity().getBody().getLinearVelocity());
        if (!getEntity().getBody().isOnGround()) {
            movement = new Point2D(movement.getX(), 0); // no jump if in air
            movement = movement.multiply(AIR_FRICTION_FACTOR);
        }

        /*
         * if (desiredMovement.getY() != 0) {
         * // we jump only once
         * desiredMovement = new Point2D(desiredMovement.getX(), 0);
         * } else {
         * movement = new Point2D(movement.getX(), 0);
         * }
         */

        // movement = movement.multiply(dt);

        if (!movement.equals(Point2D.ZERO)) {
            getEntity().getBody().applyImpulse(movement);
        }
    }

    @Override
    public void move(final Point2D directionVector) {
        final double hf = directionVector.getX() > 0 ? 1 : directionVector.getX() < 0 ? -1 : 0;
        final double vf = directionVector.getY() > 0 ? 1 : 0;
        setDesiredMovement(new Point2D(walkingSpeed * hf, jumpingSpeed * vf));
    }

    @Override
    public State getState() {
        return getDesiredMovement().getY() > 0 ? State.JUMPING
                : getDesiredMovement().getX() > 0 ? State.WALKING : State.IDLE;
    }
}
