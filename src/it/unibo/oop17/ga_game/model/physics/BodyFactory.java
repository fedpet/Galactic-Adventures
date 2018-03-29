package it.unibo.oop17.ga_game.model.physics;

import java.util.List;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
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
     * @return @EntityBody
     */
    EntityBody createCreature(Point2D position, Dimension2D size);

    /**
     * Terrain bodies are solid, not subject to forces and unmoveable.
     * Prefer this method over manually creating a grid of custom bodies because physics engine may need special tuning
     * to handle connected bodies.
     * 
     * @param topLeft
     *            Top-left point relative to the grid
     * @param cellSize
     *            Width and height of a single cell
     * @param collisionGrid
     *            The @CollisionGrid
     * @return List of @EntityBody
     */
    List<EntityBody> createTerrainFromGrid(Point2D topLeft, Dimension2D cellSize, CollisionGrid collisionGrid);

    /**
     * An item is not solid and cannot move.
     * 
     * @param position
     *            The position
     * @param size
     *            The size
     * @return The @EntityBody
     */
    EntityBody createItem(Point2D position, Dimension2D size);

    /**
     * Static bodies are solid, not subject to forces and unmoveable.
     * 
     * @param position
     *            The position
     * @param size
     *            The size
     * @return The @EntityBody
     */
    EntityBody createStatic(Point2D position, Dimension2D size);

    /**
     * @return a @BodyBuilder to create custom bodies.
     */
    BodyBuilder custom();
}
