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
     * Starts the death animation for the entity.
     * 
     * @param startingPoint
     *            The initial point for the falling animation.
     * @param entity
     *            The reference entity
     */
    void deathAnimation(Point2D startingPoint);

}
