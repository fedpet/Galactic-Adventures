package it.unibo.oop17.ga_game.model;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Function;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventSubscriber;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Runs the game world simulation.
 */
public final class GameWorldImpl implements GameWorld {
    private static final Point2D GRAVITY = new Point2D(0, -60);
    private final PhysicsEngine engine = PhysicsEngine.create(GRAVITY);
    private final Set<Entity> entities = new LinkedHashSet<>();
    private final Set<Entity> removedEntities = new LinkedHashSet<>();
    private final TriggerLinker linker = new TriggerLinker();
    private final EntityEventSubscriber myListener = new MyListener();

    @Override
    public void addTerrain(final Point2D position, final Dimension2D size) {
        engine.bodyBuilder()
                .position(position)
                .size(size)
                .moveable(false)
                .friction(0)
                .build();
    }

    @Override
    public <E extends Entity> E spawnEntity(final Function<BodyBuilder, E> spawner) {
        final E entity = spawner.apply(engine.bodyBuilder());
        entities.add(entity);
        entity.register(myListener);
        linker.track(entity);
        return entity;
    }

    @Override
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

    private final class MyListener implements EntityEventSubscriber {
        @Subscribe
        public void onEntityDestruction(final DestructionEvent destruction) {
            removedEntities.add(destruction.getSource());
        }
    }
}
