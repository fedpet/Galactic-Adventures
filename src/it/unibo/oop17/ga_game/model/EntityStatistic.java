package it.unibo.oop17.ga_game.model;

import java.util.Set;

/**
 * What did an entity do in a level?
 */
public interface EntityStatistic {
    /**
     * @return amount of money collected
     */
    int getMoneyCollected();

    /**
     * @return keys collected
     */
    Set<KeyLockType> getKeysCollected();

    /**
     * @return number of enemies killed
     */
    int getEnemiesKilled();
}
