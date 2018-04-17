package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.entities.events.InventoryChangedEvent;

/**
 * Basic implementation of @Inventory.
 * It generates a @InventoryChangedEvent on change.
 */
public final class InventoryImpl extends AbstractEntityComponent implements Inventory {
    private final Set<KeyLockType> keys = new HashSet<>();
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
    public void add(final KeyLockType key) {
        keys.add(key);
        notifyChange();
    }

    @Override
    public Set<KeyLockType> getKeysBunch() {
        return Collections.unmodifiableSet(keys);
    }

    private void notifyChange() {
        post(new InventoryChangedEvent(getEntity()));
    }
}
