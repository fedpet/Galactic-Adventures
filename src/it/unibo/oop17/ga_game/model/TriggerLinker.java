package it.unibo.oop17.ga_game.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Triggerable;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventSubscriber;
import it.unibo.oop17.ga_game.model.entities.events.PasswordTriggeredEvent;

/**
 * It keeps track of entities' triggerable component in the game world and it triggers them at
 * a {@link PasswordTriggeredEvent} object reception.
 */
public class TriggerLinker {
    private final Map<String, Set<Triggerable>> map = new HashMap<>();
    private final MyTriggerListener listener = new MyTriggerListener();

    /**
     * It starts to keep track of an entity and stores its eventual triggerable component.
     * 
     * @param entity
     *            The entity to keep track of.
     */
    public void track(final Entity entity) {
        entity.register(listener);
        entity.get(Triggerable.class).ifPresent(component -> {
            map.merge(component.getPassword(), new HashSet<>(Arrays.asList(component)), (x, y) -> {
                x.addAll(y);
                return x;
            });
        });
    }

    /**
     * It stops to keep track of an entity and removes from the storage its eventual triggerable component.
     * 
     * @param entity
     *            The entity to stop to keep track of.
     */
    public void untrack(final Entity entity) {
        entity.unregister(listener);
        entity.get(Triggerable.class).ifPresent(component -> {
            map.remove(component.getPassword());
        });
    }


    private final class MyTriggerListener implements EntityEventSubscriber {
        @Subscribe
        public void activateEvent(final PasswordTriggeredEvent event) {
            map.getOrDefault(event.getPassword(), Collections.emptySet()).forEach(Triggerable::trigger);
        }
    }
}
