package it.unibo.oop17.ga_game.model;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Runs the game world simulation.
 */
public class GameWorld {
    private static final Point2D GRAVITY = new Point2D(0, -60);
    private final PhysicsEngine engine = PhysicsEngine.create(GRAVITY);
    private final Set<Entity> entities = new LinkedHashSet<>();
    private final Set<Entity> removedEntities = new LinkedHashSet<>();
    private final TriggerLinker linker = new TriggerLinker();
    private final EntityEventListener myListener = new MyListener();

    /**
     * Adds a block of static terrain to the world.
     * 
     * @param position
     *            The center of the block.
     * @param size
     *            The size of the block in meters.
     */
    public void addTerrain(final Point2D position, final Dimension2D size) {
        engine.bodyBuilder()
                .position(position)
                .size(size)
                .moveable(false)
                .friction(0)
                .build();
    }

    /**
     * Spawns an entity.
     * The entity's body must be built with the given @BodyBuilder.
     * 
     * @param <E>
     *            The type of the entity to spawn.
     * @param spawner
     *            A function making an @Entity with the provided @BodyBuilder and returning it
     * @return The spawned entity.
     */
    public <E extends Entity> E spawnEntity(final Function<BodyBuilder, E> spawner) {
        final E entity = spawner.apply(engine.bodyBuilder());
        entities.add(entity);
        entity.register(myListener);
        linker.track(entity);
        return entity;
    }

    /**
     * Advance the world simulation.
     * 
     * @param dt
     *            Time in seconds to simulate
     */
    public void update(final double dt) {
        entities.stream()
                .filter(e -> !removedEntities.contains(e))
                .forEach(e -> e.update(dt));
        engine.update(dt);
        removedEntities.forEach(this::remove);
        removedEntities.clear();
    }

    private void remove(final Entity entity) {
        linker.untrack(entity);
        entities.remove(entity);
        engine.remove(entity.getBody());
    }

    private final class MyListener implements EntityEventListener {
        @Subscribe
        public void onEntityDestruction(final DestructionEvent destruction) {
            removedEntities.add(destruction.getSource());
        }
    }
}
