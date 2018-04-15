package it.unibo.oop17.ga_game.view.entities;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models a generic {@link Entity} view.
 */
public interface EntityView {

    /**
     *  Used to set the entity view position.
     * 
     * @param worldPointToFX
     *            The new position to set for the entity view.
     */
    void setPosition(Point2D worldPointToFX);

    /**
     * Used to set the entity view dimension.
     * 
     * @param dimension
     *            The dimension to set for the entity view.
     */
    void setDimension(Dimension2D dimension);

    /**
     * @return the current entity view position.
     */
    Point2D getPosition();

    /**
     * Used to remove the entity view from the world view.
     */
    void remove();


}
