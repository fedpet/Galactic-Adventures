package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.view.entities.CoinType;
import it.unibo.oop17.ga_game.view.entities.CoinView;
import it.unibo.oop17.ga_game.view.entities.DoorView;
import it.unibo.oop17.ga_game.view.entities.FlyingEnemyView;
import it.unibo.oop17.ga_game.view.entities.JumpingPlatformView;
import it.unibo.oop17.ga_game.view.entities.KeyView;
import it.unibo.oop17.ga_game.view.entities.LeverView;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;
import it.unibo.oop17.ga_game.view.entities.LivingEntityView;
import it.unibo.oop17.ga_game.view.entities.LockView;
import it.unibo.oop17.ga_game.view.entities.MovingPlatformView;
import it.unibo.oop17.ga_game.view.entities.PlayerView;
import it.unibo.oop17.ga_game.view.entities.SlimeEnemyView;
import it.unibo.oop17.ga_game.view.entities.SpikesView;
import it.unibo.oop17.ga_game.view.entities.TriggerEntityView;
import javafx.scene.Group;

/**
 * Creates entities.
 */
public final class EntityViewFactoryImpl implements EntityViewFactory {
    private final Group parent;

    /**
     * Constructor of EntityViewFactory.
     * @param parent
     *          The group parent.
     */
    public EntityViewFactoryImpl(final Group parent) {
        this.parent = parent;
    }

    @Override
    public LivingEntityView createPlayer() {
        return new PlayerView(parent);
    }

    @Override
    public LivingEntityView createSlime() {
        return new SlimeEnemyView(parent);
    }

    @Override
    public LivingEntityView createBee() {
        return new FlyingEnemyView(parent);
    }

    @Override
    public TriggerEntityView createDoor() {
        return new DoorView(parent, false);
    }

    @Override
    public LifelessEntityView createMovingPlatform() {
        return new MovingPlatformView(parent);
    }

    @Override
    public TriggerEntityView createJumpingPlatform() {
        return new JumpingPlatformView(parent);
    }

    @Override
    public TriggerEntityView createLever() {
        return new LeverView(parent, false);
    }

    @Override
    public LifelessEntityView createCoin(final CoinType type) {
        return new CoinView(parent, type);
    }

    @Override
    public LifelessEntityView createLock(final KeyLockType type) {
        return new LockView(parent, type);
    }

    @Override
    public LifelessEntityView createKey(final KeyLockType type) {
        return new KeyView(parent, type);
    }

    @Override
    public LifelessEntityView createSpikes() {
        return new SpikesView(parent);
    }
}
