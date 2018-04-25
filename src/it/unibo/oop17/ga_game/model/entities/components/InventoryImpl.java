package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.unibo.oop17.ga_game.model.entities.KeyType;
import it.unibo.oop17.ga_game.model.entities.events.InventoryChangedEvent;

/**
 * Basic implementation of {@link Inventory}.
 * It generates a {@link InventoryChangedEvent instance} on change.
 */
public final class InventoryImpl extends AbstractEntityComponent implements Inventory {
    private final Set<KeyType> keys = new HashSet<>();
    private int money;

    @Override
    public void addMoney(final int amount) {
        money += amount;
        notifyChange();
    }

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void add(final KeyType key) {
        keys.add(key);
        notifyChange();
    }

    @Override
    public Set<KeyType> getKeysBunch() {
        return Collections.unmodifiableSet(keys);
    }

    private void notifyChange() {
        post(new InventoryChangedEvent(getEntity()));
    }
}
