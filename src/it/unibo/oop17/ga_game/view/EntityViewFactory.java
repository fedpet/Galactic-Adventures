package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.view.entities.CoinType;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;
import it.unibo.oop17.ga_game.view.entities.PlayerView;
import it.unibo.oop17.ga_game.view.entities.TriggerEntityView;

/**
 * Factory of Entities.
 */
public interface EntityViewFactory {

    /**
     * @return a player.
     */
    PlayerView createPlayer();

    /**
     * @return a slime enemy.
     */
    LivingEntityView createSlime();

    /**
     * @return a bee enemy.
     */
    LivingEntityView createBee();

    /**
     * @return a door.
     */
    TriggerEntityView createDoor();

    /**
     * @return a moving platform.
     */
    LifelessEntityView createMovingPlatform();

    /**
     * @return a spring.
     */
    TriggerEntityView createJumpingPlatform();

    /**
     * @return a lever.
     */
    TriggerEntityView createLever();

    /**
     * @param type
     *          The type of coin.
     * @return a coin.
     */
    LifelessEntityView createCoin(CoinType type);

    /**
     * @param type
     *          The type of lock.
     * @return a lock.
     */
    LifelessEntityView createLock(KeyLockType type);

    /**
     * @param type
     *          The type of key.
     * @return a key.
     */
    LifelessEntityView createKey(KeyLockType type);

    /**
     * @return a block of spikes.
     */
    LifelessEntityView createSpikes();

}
