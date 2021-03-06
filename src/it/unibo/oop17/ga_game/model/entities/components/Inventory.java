package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Set;

import it.unibo.oop17.ga_game.model.entities.KeyType;

/**
 * Inventories can contain money and keys.
 */
public interface Inventory extends EntityComponent {
    /**
     * @return Amount of money
     */
    int getMoney();

    /**
     * Adds money.
     * 
     * @param amount
     *            amount of money to add.
     */
    void addMoney(int amount);

    /**
     * @return The keys bunch.
     */
    Set<KeyType> getKeysBunch();

    /**
     * Adds the key.
     * 
     * @param key
     *            the key type
     */
    void add(KeyType key);
}
