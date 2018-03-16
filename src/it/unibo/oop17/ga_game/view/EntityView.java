package it.unibo.oop17.ga_game.view;

import java.util.Map;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;

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

    /**
     * @return the mapped entity view animations.
     */
    Map<MovementComponent.State, Runnable> getAnimations();

    /**
     * @return the entity image view.
     */
    ImageView getView();

}
