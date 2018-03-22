package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.geometry.Point2D;

public interface LivingEntityView extends EntityView {

    /**
     * Used to change the entity view movement.
     * 
     * @param state
     *            The state to be associated to the entity view movement.
     */
    void changeMovement(State state);

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
