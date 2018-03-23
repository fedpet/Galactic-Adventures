package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;

public interface LivingEntityView extends StateChangingEntityView<MovementComponent.State> {

    /**
     * Used to change the entity view direction.
     * 
     * @param direction
     *            The direction to set for the entity view.
     */
    void changeFaceDirection(HorizontalDirection direction);

    /**
     * Used to change the entity view movement.
     * 
     * @param state
     *            The state to be associated to the entity view movement.
     */
    // void changeMovement(State state);

    /**
     * Used to calculate the next point of the entity view when dying.
     * 
     * @param startingPoint
     *            The initial point for the falling animation.
     * 
     * @return the current entity view dying position.
     */
    Point2D updatePointFromDeath(Point2D startingPoint);

    /**
     * Starts the death animation for the entity.
     * 
     * @param entity
     *            The reference entity
     */
    void deathAnimation();

}
