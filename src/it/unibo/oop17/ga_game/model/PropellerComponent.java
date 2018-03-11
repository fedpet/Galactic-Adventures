package it.unibo.oop17.ga_game.model;

import javafx.geometry.Point2D;

public class PropellerComponent extends AbstractMovementComponent {
    private static final double MIN_THRESHOLD = 0.01;
    private final double speed;

    public PropellerComponent(final double speed) {
        this.speed = speed;
    }

    @Override
    public void move(final Point2D direction) {
        if (direction.equals(Point2D.ZERO)) {
            setDesiredMovement(Point2D.ZERO);
        } else {
            final double r = radians(direction);
            setDesiredMovement(new Point2D(speed * Math.cos(r), speed * Math.sin(r)));
        }

        if (Math.abs(getDesiredMovement().getX()) < MIN_THRESHOLD) {
            setDesiredMovement(new Point2D(0, getDesiredMovement().getY()));
        }

        if (Math.abs(getDesiredMovement().getY()) < MIN_THRESHOLD) {
            setDesiredMovement(new Point2D(getDesiredMovement().getX(), 0));
        }
    }

    @Override
    public State getState() {
        return getDesiredMovement().equals(Point2D.ZERO) ? State.IDLE : State.FLYING;
    }

    @Override
    public void update(final double dt) {
        if (!getDesiredMovement().equals(Point2D.ZERO)) {
            Point2D movement = getDesiredMovement().subtract(getEntity().getBody().getLinearVelocity());
            if (getDesiredMovement().getY() == 0) {
                // so we don't remove gravity
                movement = new Point2D(movement.getX(), 0);
            }
            // movement = movement.multiply(dt);

            if (!movement.equals(Point2D.ZERO)) {
                getEntity().getBody().applyImpulse(movement);
            }
        }
    }

    private double radians(final Point2D direction) {
        // https://stackoverflow.com/questions/17530169/get-angle-between-point-and-origin
        final Point2D reference = new Point2D(1, 0);
        return -(Math.atan2(reference.getY(), reference.getX())
                - Math.atan2(direction.getY(), direction.getX()));
    }

}
