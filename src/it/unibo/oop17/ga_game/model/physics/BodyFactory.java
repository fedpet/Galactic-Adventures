package it.unibo.oop17.ga_game.model.physics;

import java.util.List;

import it.unibo.oop17.ga_game.model.EntityBody;
import it.unibo.oop17.ga_game.utils.CollisionGrid;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Factory for EntityBodies.
 */
public interface BodyFactory {
    /**
     * 
     * @param position
     *            Position (relative to the body's center)
     * @param size
     *            Width and height
     * @return {@link EntityBody}
     */
    EntityBody createCreature(Point2D position, Dimension2D size);

    /**
     * Terrain bodies are not subject to forces and won't move.
     * 
     * @param topLeft
     *            Top-left point relative to the grid
     * @param cellSize
     *            Width and height of a single cell
     * @param collisionGrid
     *            The @CollisionGrid
     * @return List of {@link EntityBody}
     */
    List<EntityBody> createTerrainFromGrid(Point2D topLeft, Dimension2D cellSize, CollisionGrid collisionGrid);
}
