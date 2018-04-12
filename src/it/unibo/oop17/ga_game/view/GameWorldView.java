package it.unibo.oop17.ga_game.view;

import org.mapeditor.core.TileLayer;

import it.unibo.oop17.ga_game.controller.PlayerInput;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public interface GameWorldView {
    
    EntityViewFactory entityFactory();

    void showTerrain(final TileLayer layer, final Point2D topLeft, final Dimension2D tileSize);

    PlayerInput getPlayerInput();
}
