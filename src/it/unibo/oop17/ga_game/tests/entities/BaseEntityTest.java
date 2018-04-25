package it.unibo.oop17.ga_game.tests.entities;

import java.util.function.Function;

import org.junit.Before;

import it.unibo.oop17.ga_game.model.GameWorld;
import it.unibo.oop17.ga_game.model.GameWorldImpl;
import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Base class for entities tests.
 * Builds and manage the a {@link GameWorld} instance offering utilities to place entities and advance the simulation.
 */
public class BaseEntityTest {
    private static final double DELAY_PER_STEP = 1.0 / 60;
    private GameWorld world = new GameWorldImpl();

    /**
     * Initializes world.
     */
    @Before
    public void setUp() {
        world = new GameWorldImpl();
    }

    /**
     * Simulate the world for the provided amount of time.
     * See {@link GameWorld#update}.
     * 
     * @param seconds
     *            in seconds
     */
    protected void advanceSimulation(final double seconds) {
        int steps = (int) Math.ceil(seconds / DELAY_PER_STEP);
        for (; steps >= 0; steps--) {
            world.update(DELAY_PER_STEP);
        }
    }

    /**
     * See {@link GameWorld#spawnEntity}.
     * 
     * @param <E>
     *            the type of the entity
     * @param spawner
     *            spawner
     * @return the spawned entity.
     */
    protected <E extends Entity> E spawnEntity(final Function<BodyBuilder, E> spawner) {
        return world.spawnEntity(spawner);
    }

    /**
     * Spawns a moveable and composable {@link TestEntity} instance.
     * 
     * @param position
     *            position in meters
     * @param size
     *            size in meters
     * @return the test entity.
     */
    protected TestEntity spawnTestEntity(final Point2D position, final Dimension2D size) {
        return world.spawnEntity(body -> {
            return new TestEntity(body.position(position).size(size).build());
        });
    }

    /**
     * See {@link GameWorld#addTerrain}.
     * 
     * @param position
     *            the center of the terrain block
     * @param size
     *            the size of the block in meters
     */
    protected void addTerrain(final Point2D position, final Dimension2D size) {
        world.addTerrain(position, size);
    }
}
