
package it.unibo.oop17.ga_game.model;

import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;

/**
 * Basic entity capable of walking and jumping.
 * TODO: refactor this class
 */
public abstract class WalkingEntity extends AbstractEntity implements Entity {
    private Point2D desiredMovement = Point2D.ZERO;

    /**
     * 
     * @param world
     *            The world this entity lives in.
     * @param rect
     *            The shape of its Body
     */
    public WalkingEntity(final EntityBody body, final Brain brain) {
        super(body, brain);
    }

    @Override
    public void update(final double dt) {
        Point2D movement = desiredMovement.subtract(getBody().getLinearVelocity());
        if (!getBody().isOnGround()) {
            movement = movement.multiply(0.5);
        }

        if (desiredMovement.getY() != 0) {
            desiredMovement = new Point2D(desiredMovement.getX(), 0);
        } else {
            movement = new Point2D(movement.getX(), 0);
        }

        if (!movement.equals(Point2D.ZERO)) {
            getBody().applyImpulse(movement);
        }

    }

    public HorizontalDirection getMovingDirection() {
        if (desiredMovement.getX() < 0) {
            return HorizontalDirection.LEFT;
        }
        return HorizontalDirection.RIGHT;
    }

    public void move(final HorizontalDirection direction) {
        desiredMovement = new Point2D(direction == HorizontalDirection.RIGHT ? getWalkSpeed() : -getWalkSpeed(),
                desiredMovement.getY());
    }

    public void stopWalking() {
        desiredMovement = new Point2D(0, desiredMovement.getY());
    }

    public void jump() {
        if (getBody().isOnGround()) {
            desiredMovement = new Point2D(desiredMovement.getX(), getJumpSpeed());
        }
    }

    protected abstract float getJumpSpeed();

    protected abstract float getWalkSpeed();
}