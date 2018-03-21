package it.unibo.oop17.ga_game.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.TriggerableComponent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import it.unibo.oop17.ga_game.model.entities.events.TriggerEvent;

public class TriggerLinker {
    private final Map<String, Set<TriggerableComponent>> map = new HashMap<>();
    private final MyTriggerListener listener = new MyTriggerListener();

    public void track(Entity entity) {
        entity.register(listener);
        entity.get(TriggerableComponent.class).ifPresent(component -> {
            map.merge(component.getPassword(), new HashSet<>(Arrays.asList(component)), (x, y) -> {
                x.addAll(y);
                return x;
            });
        });
    }

    public void untrack(Entity entity) {
        entity.unregister(listener);
        entity.get(TriggerableComponent.class).ifPresent(component -> {
            map.remove(component.getPassword());
        });
    }


    private final class MyTriggerListener implements EntityEventListener {
        @Subscribe
        public void activateEvent(final TriggerEvent event) {
            map.get(event.getPassword()).forEach(TriggerableComponent::trigger);
        }
    }
}
