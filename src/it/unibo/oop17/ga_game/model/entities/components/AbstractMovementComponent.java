package it.unibo.oop17.ga_game.model.entities.components;

import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;

/**
 * Base class for @MovementComponent.
 */
public abstract class AbstractMovementComponent extends AbstractEntityComponent implements MovementComponent {
    private Point2D desiredMovement = Point2D.ZERO;


    /**
     * @return HorizontalDirection based on getDesiredMovement().getX()
     */
    @Override
    public HorizontalDirection getFaceDirection() {
        return desiredMovement.getX() > 0 ? HorizontalDirection.RIGHT : HorizontalDirection.LEFT;
    }

    /**
     * Sets the desired movement vector.
     * 
     * @param movement
     *            The movement
     */
    protected void setDesiredMovement(final Point2D movement) {
        desiredMovement = movement;
    }

    /**
     * 
     * @return The desired movement vector.
     */
    protected Point2D getDesiredMovement() {
        return desiredMovement;
    }
}
