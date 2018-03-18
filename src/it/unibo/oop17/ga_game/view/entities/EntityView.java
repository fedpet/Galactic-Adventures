package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.geometry.Dimension2D;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.VerticalDirection;

public interface EntityView {

    /**
     * Used to set the entity view position.
     * 
     * @param dt
     *            The new position to set for the entity view.
     */
    void setPosition(Point2D worldPointToFX);

    /**
     * Used to set the entity view dimension.
     * 
     * @param dt
     *            The dimension to set for the entity view.
     */
    void setDimension(Dimension2D dimension);

    /**
     * @return the current entity view position.
     */
    Point2D getPosition();

    void remove();

    /**
     * Used to change the entity view movement.
     * 
     * @param state
     *            The state to be associated to the entity view movement.
     */
    void changeMovement(State state);

    /**
     * Used to change the entity view direction.
     * 
     * @param direction
     *            The direction to set for the entity view.
     */
    void changeFaceDirection(HorizontalDirection direction);

    /**
     * Used to change the entity view vertical direction.
     * 
     * @param direction
     *            The vertical direction to set for the entity view.
     */
    void flip(VerticalDirection direction);

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
    void deathAnimation(Entity entity);
}
