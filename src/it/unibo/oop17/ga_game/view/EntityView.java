package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public interface EntityView extends EntityEventListener {

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

}