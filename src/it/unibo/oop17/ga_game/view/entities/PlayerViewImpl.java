package it.unibo.oop17.ga_game.view.entities;

import java.util.Set;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.view.HudView;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.transform.Scale;
import javafx.util.Duration;

/**
 * Models a @Player view.
 */
public final class PlayerViewImpl extends AbstractLivingEntityView implements PlayerView {
    private static final int WIDTH = 72, HEIGHT = 97;
    private static final Image IMG_HURT = new Image("/p1_hurt.png");
    private static final double FRAME_DURATION = 700;
    private static final int PAIN_ANIM_DURATION = 300; // ms
    private final HudView hud;
    private CreatureState currentState = CreatureState.IDLE;

    /**
     * @param group
     *            The @Group in which the player view is added.
     */
    public PlayerViewImpl(final Group group, final HudView hud) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        this.hud = hud;
        mapAnimation(CreatureState.IDLE, justAnImage(new Image("/p1_stand.png")));
        mapAnimation(CreatureState.WALKING,
                aSpriteAnimation(new Image("/p1_walk.png"), Duration.millis(FRAME_DURATION), 10));
        mapAnimation(CreatureState.JUMPING, justAnImage(new Image("/p1_jump.png")));
        mapAnimation(CreatureState.SUFFERING, painAnimation());

        startAnimation(currentState);
    }

    @Override
    public void changeState(final CreatureState state) {
        if (state == CreatureState.JUMPING) {
            new AudioClip(SFX.JUMP.getPath()).play();
        } else if (state == CreatureState.IDLE && currentState == CreatureState.SUFFERING) {
            return; // don't stop suffer animation in this case.
        }
        currentState = state;
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

    private Runnable painAnimation() {
        return () -> {
            new AudioClip(SFX.PLAYER_DAMAGE.getPath()).play();
            setImage(IMG_HURT);
            final Timeline anim = new Timeline(
                    new KeyFrame(Duration.millis(PAIN_ANIM_DURATION), e -> {
                        currentState = CreatureState.IDLE;
                        changeState(CreatureState.IDLE);
                    }));
            setAnimation(anim);
        };
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
    public void setKeys(final Set<KeyLockType> keys) {
        hud.setKeys(keys);
    }

    @Override
    public void setMoney(final int money) {
        hud.setMoney(money);
    }
}
