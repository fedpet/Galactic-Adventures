package it.unibo.oop17.ga_game.view.entities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public final class PlayerView extends AbstractLivingEntityView {
    private static final int WIDTH = 72, HEIGHT = 97;
    private static final Image IMG_HURT = new Image("/p1_hurt.png");
    private static final int PAIN_ANIM_DURATION = 300; // ms
    private CreatureState previousState = CreatureState.IDLE;

    public PlayerView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(CreatureState.IDLE, justAnImage(new Image("/p1_stand.png")));
        mapAnimation(CreatureState.WALKING, aSpriteAnimation(new Image("/p1_walk.png"), Duration.millis(700), 10));
        mapAnimation(CreatureState.JUMPING, justAnImage(new Image("/p1_jump.png")));
        mapAnimation(CreatureState.SUFFERING, painAnimation());

        startAnimation(CreatureState.IDLE);
    }

    @Override
    public void changeState(final CreatureState state) {
        super.changeState(state);
        previousState = state;
    }

    private Runnable painAnimation() {
        return () -> {
            final CreatureState previous = previousState;
            setImage(IMG_HURT);
            setAnimation(new Timeline(
                    new KeyFrame(Duration.millis(PAIN_ANIM_DURATION), e -> {
                        super.changeState(previous);
                    })));
        };
    }
}
