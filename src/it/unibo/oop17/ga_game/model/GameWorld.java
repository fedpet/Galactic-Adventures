package it.unibo.oop17.ga_game.model;

import java.util.function.Function;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Runs the game world simulation.
 */
public interface GameWorld {

    /**
     * Adds a block of static terrain to the world.
     * 
     * @param position
     *            The center of the block.
     * @param size
     *            The size of the block in meters.
     */
    void addTerrain(Point2D position, Dimension2D size);

    /**
     * Spawns an entity.
     * The entity's body must be built with the given {@link BodyBuilder} object.
     * 
     * @param <E>
     *            The type of the entity to spawn.
     * @param spawner
     *            A function making an {@link Entity} with the provided {@link BodyBuilder} object and returning it
     * @return The spawned entity.
     */
    <E extends Entity> E spawnEntity(Function<BodyBuilder, E> spawner);

    /**
     * Advance the world simulation.
     * 
     * @param dt
     *            Time in seconds to simulate
     */
    void update(double dt);
}
