package it.unibo.oop17.ga_game.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.TriggerableComponent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventSubscriber;
import it.unibo.oop17.ga_game.model.entities.events.PasswordTriggeredEvent;

/**
 * It keeps track of entities' @TriggerableComponent objects in the @GameWorld and it triggers them at
 * a @PasswordTriggeredEvent.
 */
public class TriggerLinker {
    private final Map<String, Set<TriggerableComponent>> map = new HashMap<>();
    private final MyTriggerListener listener = new MyTriggerListener();

    /**
     * It starts to keep track of an @Entity and stores its eventual @TriggeableComponent.
     * 
     * @param entity
     *            The @Entity to keep track of.
     */
    public void track(final Entity entity) {
        entity.register(listener);
        entity.get(TriggerableComponent.class).ifPresent(component -> {
            map.merge(component.getPassword(), new HashSet<>(Arrays.asList(component)), (x, y) -> {
                x.addAll(y);
                return x;
            });
        });
    }

    /**
     * It stops to keep track of an @Entity and removes from the storage its eventual @TriggeableComponent.
     * 
     * @param entity
     *            The @Entity to stop to keep track of.
     */
    public void untrack(final Entity entity) {
        entity.unregister(listener);
        entity.get(TriggerableComponent.class).ifPresent(component -> {
            map.remove(component.getPassword());
        });
    }


    private final class MyTriggerListener implements EntityEventSubscriber {
        @Subscribe
        public void activateEvent(final PasswordTriggeredEvent event) {
            map.getOrDefault(event.getPassword(), Collections.emptySet()).forEach(TriggerableComponent::trigger);
        }
    }
}
