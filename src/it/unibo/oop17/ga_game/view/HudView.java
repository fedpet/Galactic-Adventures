package it.unibo.oop17.ga_game.view;

import java.util.Set;

import it.unibo.oop17.ga_game.model.entities.KeyType;

/**
 *  Interface for controlling the heads-up display.
 */
public interface HudView {
    /**
     * Sets max health level.
     * 
     * @param max
     *            Max health
     */
    void setMaxHealth(int max);

    /**
     * Sets current health level.
     * 
     * @param life
     *            current health level
     */
    void setCurrentHealth(int life);

    /**
     * Set collected keys.
     * 
     * @param keys
     *            Set of collected keys
     */
    void setKeys(Set<KeyType> keys);

    /**
     * Set amount of money.
     * 
     * @param money
     *            amount of money
     */
    void setMoney(int money);
}
