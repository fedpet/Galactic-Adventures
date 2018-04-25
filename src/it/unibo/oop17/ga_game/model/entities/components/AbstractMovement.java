package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.MovementEvent;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;

/**
 * Base class for {@link Movement}.
 */
public abstract class AbstractMovement extends AbstractContactAwareComponent implements Movement {
    private Point2D desiredMovement = Point2D.ZERO;
    private State currentState = Movement.State.IDLE;
    private HorizontalDirection faceDirection = HorizontalDirection.RIGHT;

    @Override
    public final HorizontalDirection getFaceDirection() {
        return faceDirection;
    }

    @Override
    public final State getState() {
        return currentState;
    }

    /**
     * Applies movement.
     */
    @Override
    public void update(final double dt) {
        super.update(dt);
        final Point2D movementVector = computeMovement(dt);
        if (!movementVector.equals(Point2D.ZERO)) {
            applyMovement(getEntity().getBody(), movementVector);
        }
    }

    /**
     * Returns the movement vector to be applied to the body.
     * 
     * @param dt
     *            Delta time in seconds since last call.
     * @return Movement vector to be applied
     */
    protected abstract Point2D computeMovement(double dt);

    /**
     * Apply force to the entity's body to move it.
     * 
     * @param body
     *            The owner body
     * @param force
     *            Movement force
     */
    protected void applyMovement(final EntityBody body, final Point2D force) {
        body.applyImpulse(force);
    }

    /**
     * Sets the new state and generates an event if needed.
     * 
     * @param newState
     *            the new state
     */
    protected final void setState(final State newState) {
        if (!currentState.equals(newState)) {
            currentState = newState;
            post(new MovementEvent(getEntity(), newState));
        }
    }

    /**
     * @return The desired movement vector.
     */
    protected Point2D getDesiredMovement() {
        return desiredMovement;
    }

    /**
     * Sets the desired movement vector.
     * It also changes the face direction accordingly.
     * 
     * @param movement
     *            The movement
     */
    protected void setDesiredMovement(final Point2D movement) {
        desiredMovement = movement;
        setFaceDirection(computeFaceDirection());
    }

    /**
     * Sets the new face direction.
     * 
     * @param newDir
     *            the new direction
     */
    protected final void setFaceDirection(final HorizontalDirection newDir) {
        if (newDir != faceDirection) {
            faceDirection = newDir;
            post(new FaceDirectionEvent(getEntity(), faceDirection));
        }
    }

    /**
     * 
     * @return The face direction based on horizontal velocity.
     */
    protected HorizontalDirection computeFaceDirection() {
        return desiredMovement.getX() > 0 ? HorizontalDirection.RIGHT
                : desiredMovement.getX() < 0 ? HorizontalDirection.LEFT : faceDirection;
    }
}
