package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.utils.FXUtils;
import javafx.geometry.Point2D;

/**
 * A basic propeller which can fly to any direction.
 */
public class Propeller extends AbstractMovement {
    /**
     * Minimum speed below which it completely stops.
     */
    private static final double MIN_SPEED = 0.01;
    private final double speed;

    /**
     * @param speed
     *            Propeller's power
     */
    public Propeller(final double speed) {
        super();
        this.speed = speed;
    }

    /**
     * Move towards the given direction.
     * 
     * @param direction
     *            the direction vector
     */
    @Override
    public void move(final Point2D direction) {
        if (direction.equals(Point2D.ZERO)) {
            setDesiredMovement(Point2D.ZERO);
        } else {
            final double r = FXUtils.radians(direction);
            setDesiredMovement(new Point2D(speed * Math.cos(r), speed * Math.sin(r)));
        }

        if (Math.abs(getDesiredMovement().getX()) < MIN_SPEED) {
            setDesiredMovement(new Point2D(0, getDesiredMovement().getY()));
        }

        if (Math.abs(getDesiredMovement().getY()) < MIN_SPEED) {
            setDesiredMovement(new Point2D(getDesiredMovement().getX(), 0));
        }
        updateState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Point2D computeMovement(final double dt) {
        Point2D movement = getDesiredMovement().subtract(getEntity().getBody().getLinearVelocity());
        if (getDesiredMovement().getY() == 0) {
            // so we don't remove gravity
            movement = new Point2D(movement.getX(), 0);
        }
        return movement;
    }

    /**
     * @return The propeller's speed.
     */
    protected double getSpeed() {
        return speed;
    }

    @Override
    protected void handleContact(final EntityBody other) {
        // no special actions yet
    }

    private void updateState() {
        setState(getDesiredMovement().equals(Point2D.ZERO) ? State.IDLE : State.FLYING);
    }
}
