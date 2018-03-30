package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.CoinType;
import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;
import it.unibo.oop17.ga_game.view.entities.TriggerEntityView;

public interface EntityViewFactory {
    
    LivingEntityView createPlayer();

    LivingEntityView createSlime();

    LivingEntityView createBee();

    TriggerEntityView createDoor();

    TriggerEntityView createJumpingPlatform();

    TriggerEntityView createLever();

    LifelessEntityView createCoin(CoinType type);

    LifelessEntityView createLock(KeyLockType type);

    LifelessEntityView createKey(KeyLockType type);

    LifelessEntityView createSpikes();
    
}
