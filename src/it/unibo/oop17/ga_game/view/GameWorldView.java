package it.unibo.oop17.ga_game.view;

import org.mapeditor.core.TileLayer;

import it.unibo.oop17.ga_game.controller.PlayerInputListener;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Interface for controlling game world view.
 */
public interface GameWorldView {

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
     * Sets a listener for player input.
     * 
     * @param listener
     *            The listener.
     */
    void setPlayerInputListener(PlayerInputListener listener);
}
