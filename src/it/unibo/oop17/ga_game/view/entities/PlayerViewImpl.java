package it.unibo.oop17.ga_game.view.entities;

import java.util.Set;

import it.unibo.oop17.ga_game.model.entities.KeyType;
import it.unibo.oop17.ga_game.view.AudioPlayer;
import it.unibo.oop17.ga_game.view.HudView;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

/**
 * Models a view for the player.
 */
public final class PlayerViewImpl extends AbstractLivingEntityView implements PlayerView {
    private static final int WIDTH = 72, HEIGHT = 97;
    private static final Image IMG_HURT = new Image("/p1_hurt.png");
    private static final double FRAME_DURATION = 700;
    private final AudioPlayer audioplayer;
    private final HudView hud;

    /**
     * @param group
     *            The {@link Group} instance in which the player view is added.
     * @param hud
     *            The {@link HudView} instance associated with the player.
     * @param audioplayer
     *            The related {@link AudioPlayer} instance.
     */
    public PlayerViewImpl(final Group group, final HudView hud, final AudioPlayer audioplayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT), audioplayer);
        this.hud = hud;
        this.audioplayer = audioplayer;
        mapAnimation(CreatureState.IDLE, justAnImage(new Image("/p1_stand.png")));
        mapAnimation(CreatureState.WALKING,
                aSpriteAnimation(new Image("/p1_walk.png"), Duration.millis(FRAME_DURATION), 10));
        mapAnimation(CreatureState.JUMPING, justAnImage(new Image("/p1_jump.png")));
        mapAnimation(CreatureState.SUFFERING, painAnimation(IMG_HURT, SFX.PLAYER_DAMAGE.getPath(), CreatureState.IDLE));

        startAnimation(getCurrentState());
    }

    @Override
    public void changeState(final CreatureState state) {
        if (state == CreatureState.JUMPING) {
            audioplayer.playSFX(SFX.JUMP.getPath());
        } else if (state == CreatureState.IDLE && getCurrentState() == CreatureState.SUFFERING) {
            return; // don't stop suffer animation in this case.
        }
        super.changeState(state);
    }

    @Override
    public void setPosition(final Point2D pos) {
        super.setPosition(pos);
        getView().getParent()
                .setTranslateX(-getView().getTranslateX() * scaling().getMxx() + getView().getScene().getWidth() / 2);
        getView().getParent()
                .setTranslateY(-getView().getTranslateY() * scaling().getMyy() + getView().getScene().getHeight() / 2);
    }

    private Scale scaling() {
        return getParentView().getTransforms().stream().filter(t -> t instanceof Scale).map(t -> (Scale) t)
                .findFirst().orElseGet(() -> new Scale(1, 1));
    }

    @Override
    public void setMaxHealth(final int max) {
        hud.setMaxHealth(max);
    }

    @Override
    public void setCurrentHealth(final int life) {
        hud.setCurrentHealth(life);
    }

    @Override
    public void setKeys(final Set<KeyType> keys) {
        hud.setKeys(keys);
    }

    @Override
    public void setMoney(final int money) {
        hud.setMoney(money);
    }
}
