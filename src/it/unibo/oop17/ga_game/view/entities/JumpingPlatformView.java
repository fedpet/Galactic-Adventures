package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.TriggerState;
import it.unibo.oop17.ga_game.view.AudioPlayer;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Models a @JumpingPlatform view.
 */
public class JumpingPlatformView extends AbstractStateChangingEntityView<TriggerState> implements TriggerEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;
    private static final double JUMP_ANIM_DURATION = 500;
    private static final Image IMG_IDLE = new Image("/jumperDown.png");
    private static final Image IMG_JUMP = new Image("/jumperUp.png");
    private final AudioPlayer audioplayer;

    /**
     * @param group
     *            The @Group in which the jumping platform view is added.
     * @param audioplayer
     *            The audio player.
     */
    public JumpingPlatformView(final Group group, final AudioPlayer audioplayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        this.audioplayer = audioplayer;
        mapAnimation(TriggerState.DEACTIVATED, justAnImage(IMG_IDLE));
        mapAnimation(TriggerState.ACTIVATED, jumpAnimation());

        changeState(TriggerState.DEACTIVATED);
    }

    private Runnable jumpAnimation() {
        return () -> {
            audioplayer.playSFX(SFX.SPRING.getPath());
            setImage(IMG_JUMP);
            setAnimation(new Timeline(
                    new KeyFrame(Duration.millis(JUMP_ANIM_DURATION), e -> {
                        changeState(TriggerState.DEACTIVATED);
                    })));
        };
    }
}
