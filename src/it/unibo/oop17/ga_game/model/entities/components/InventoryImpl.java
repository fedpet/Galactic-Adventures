package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import it.unibo.oop17.ga_game.model.KeyLockType;

/**
 * Basic implementation of @Inventory.
 */
public final class InventoryImpl extends AbstractEntityComponent implements Inventory {
    private final Set<KeyLockType> keys = new HashSet<>();
    private int money;

    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public void addMoney(final int amount) {
        money += amount;
    }

    @Override
    public Set<KeyLockType> getKeysBunch() {
        return Collections.unmodifiableSet(keys);
    }

    @Override
    public void add(final KeyLockType key) {
        keys.add(key);
    }
}
