package it.unibo.oop17.ga_game.model.entities.components;

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
        if (!canJump()) {
            movement = new Point2D(movement.getX(), 0);
            // no jump if in air
            // but we can still move a little
            movement = movement.multiply(AIR_FRICTION_FACTOR);
        }

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

    /**
     * 
     * @return true if the owner's @EntityBody is on another one.
     */
    public boolean canJump() {
        return getEntity().getBody().getContacts()
                .filter(c -> c.getPoint().getY() <= -getEntity().getBody().getDimension().getHeight() / 2)
                .findAny()
                .isPresent();
    }

    @Override
    public double getSpeed() {
        return this.walkingSpeed;
    }
}
