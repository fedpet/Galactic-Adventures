package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.MovementEvent;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;

/**
 * Base class for @MovementComponent.
 */
public abstract class AbstractMovementComponent extends AbstractEntityComponent implements MovementComponent {
    private Point2D desiredMovement = Point2D.ZERO;
    private State currentState = MovementComponent.State.IDLE;
    private HorizontalDirection faceDirection = HorizontalDirection.RIGHT;


    /**
     * @return HorizontalDirection based on getDesiredMovement().getX()
     */
    @Override
    public final HorizontalDirection getFaceDirection() {
        return faceDirection;
    }

    @Override
    public final State getState() {
        return currentState;
    }

    /**
     * Sets the new state and generates an event if needed.
     * 
     * @param newState
     *            the new state
     */
    protected final void setState(final State newState) {
        if (currentState != newState) {
            currentState = newState;
            post(new MovementEvent(newState));
        }
    }

    /**
     * 
     * @return The desired movement vector.
     */
    protected Point2D getDesiredMovement() {
        return desiredMovement;
    }

    /**
     * Sets the desired movement vector.
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
            post(new FaceDirectionEvent(faceDirection));
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
