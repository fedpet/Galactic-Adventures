package it.unibo.oop17.ga_game.view;

import org.mapeditor.core.TileLayer;

import it.unibo.oop17.ga_game.controller.PlayerInput;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Interface for controlling game world view.
 */
public interface GameWorldView extends FXView {

    /**
     * @return the entity factory.
     */
    EntityViewFactory entityFactory();

    /**
     * Shows the level terrain.
     * @param layer
     *          The current layer.
     * @param topLeft
     *          The top left point.
     * @param tileSize
     *          The tile size.
     */
    void showTerrain(TileLayer layer, Point2D topLeft, Dimension2D tileSize);

    /**
     * @return the player input.
     */
    PlayerInput getPlayerInput();
}
