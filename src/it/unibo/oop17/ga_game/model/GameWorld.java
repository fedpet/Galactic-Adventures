package it.unibo.oop17.ga_game.model;

import java.util.LinkedHashSet;
import java.util.Set;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
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

    public BodyFactory bodyFactory() {
        return engine.bodyFactory();
    }

    public void addEntity(final Entity entity) {
        entities.add(entity);
        entity.register(myListener);
        linker.track(entity);
    }

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
