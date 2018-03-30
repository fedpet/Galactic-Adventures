package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Models a {@link Player} view.
 */
public final class PlayerView extends AbstractLivingEntityView {
    private static final int WIDTH = 72, HEIGHT = 97;
    private static final Image IMG_HURT = new Image("/p1_hurt.png");
    private static final int PAIN_ANIM_DURATION = 300; // ms
    private CreatureState currentState = CreatureState.IDLE;

    /**
     * @param group
     *            The @Group in which the player view is added.
     */
    public PlayerView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(CreatureState.IDLE, justAnImage(new Image("/p1_stand.png")));
        mapAnimation(CreatureState.WALKING, aSpriteAnimation(new Image("/p1_walk.png"), Duration.millis(700), 10));
        mapAnimation(CreatureState.JUMPING, justAnImage(new Image("/p1_jump.png")));
        mapAnimation(CreatureState.SUFFERING, painAnimation());

        startAnimation(currentState);
    }

    @Override
    public void changeState(final CreatureState state) {
        if (state == CreatureState.IDLE && currentState == CreatureState.SUFFERING) {
            return; // don't stop suffer animation in this case.
        }
        currentState = state;
        super.changeState(state);
    }

    @Override
    public void setPosition(final Point2D pos) {
        super.setPosition(pos);
        getView().getParent().setTranslateX(-getView().getTranslateX() + getView().getScene().getWidth() / 2);
        getView().getParent().setTranslateY(-getView().getTranslateY() + getView().getScene().getHeight() / 2);
    }

    private Runnable painAnimation() {
        return () -> {
            setImage(IMG_HURT);
            final Timeline anim = new Timeline(
                    new KeyFrame(Duration.millis(PAIN_ANIM_DURATION), e -> {
                        currentState = CreatureState.IDLE;
                        changeState(CreatureState.IDLE);
                    }));
            setAnimation(anim);
        };
    }
}
