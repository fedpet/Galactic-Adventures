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
import it.unibo.oop17.ga_game.view.entities.PlayerViewImpl;
import it.unibo.oop17.ga_game.view.entities.SlimeEnemyView;
import it.unibo.oop17.ga_game.view.entities.SpikeView;
import it.unibo.oop17.ga_game.view.entities.TriggerEntityView;
import javafx.scene.Group;

/**
 * Creates entities.
 */
public final class EntityViewFactoryImpl implements EntityViewFactory {
    private final HudScreen hud;
    private final Group parent;
    private final AudioPlayer audioplayer;

    /**
     * Constructor of EntityViewFactory.
     * 
     * @param parent
     *            The group parent.
     * @param hud
     *            The HUD to which wire the player
     * @param audioplayer
     *            The audio player.
     */
    public EntityViewFactoryImpl(final Group parent, final HudScreen hud, final AudioPlayer audioplayer) {
        this.parent = parent;
        this.hud = hud;
        this.audioplayer = audioplayer;
    }

    @Override
    public PlayerView createPlayer() {
        return new PlayerViewImpl(parent, hud, audioplayer);
    }

    @Override
    public LivingEntityView createSlime() {
        return new SlimeEnemyView(parent, audioplayer);
    }

    @Override
    public LivingEntityView createBee() {
        return new FlyingEnemyView(parent, audioplayer);
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
        return new JumpingPlatformView(parent, audioplayer);
    }

    @Override
    public TriggerEntityView createLever() {
        return new LeverView(parent, false, audioplayer);
    }

    @Override
    public LifelessEntityView createCoin(final CoinType type) {
        return new CoinView(parent, type, audioplayer);
    }

    @Override
    public LifelessEntityView createLock(final KeyLockType type) {
        return new LockView(parent, type, audioplayer);
    }

    @Override
    public LifelessEntityView createKey(final KeyLockType type) {
        return new KeyView(parent, type, audioplayer);
    }

    @Override
    public LifelessEntityView createSpike() {
        return new SpikeView(parent);
    }
}
