package it.unibo.oop17.ga_game.model;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Inventory;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventSubscriber;
import it.unibo.oop17.ga_game.model.entities.events.HitEvent;

/**
 * Tracks statistics of an @Entity.
 */
public final class StatisticTracker implements EntityStatistic {
    private final Entity trackedEntity;
    private int numberOfEnemiesKilled;

    /**
     * @param entity
     *            The entity to be tracked.
     */
    public StatisticTracker(final Entity entity) {
        trackedEntity = Objects.requireNonNull(entity);
        trackedEntity.register(new MyEntityListener());
    }

    @Override
    public int getMoneyCollected() {
        return trackedEntity.get(Inventory.class).isPresent() ? trackedEntity.get(Inventory.class).get().getMoney() : 0;
    }

    @Override
    public Set<KeyLockType> getKeysCollected() {
        return trackedEntity.get(Inventory.class).isPresent() ? trackedEntity.get(Inventory.class).get().getKeysBunch()
                : Collections.emptySet();
    }

    @Override
    public int getEnemiesKilled() {
        return numberOfEnemiesKilled;
    }

    private final class MyEntityListener implements EntityEventSubscriber {
        @Subscribe
        public void onDestruction(final DestructionEvent event) {
            event.getSource().unregister(this);
        }

        @Subscribe
        public void onHit(final HitEvent event) {
            if (event.hasKilled()) {
                numberOfEnemiesKilled++;
            }
        }
    }
}
