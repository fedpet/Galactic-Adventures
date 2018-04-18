package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.view.AudioPlayer;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.geometry.Dimension2D;
import javafx.geometry.HorizontalDirection;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Base class for @LivingEntityView.
 */
public abstract class AbstractLivingEntityView extends AbstractStateChangingEntityView<CreatureState>
        implements LivingEntityView {

    private static final double DEATH_FALLING_RATE_SPEED = 0.6;
    private static final double DEATH_TIME = 60;
    private final AudioPlayer audioplayer;
    private int deathTimeCount;
    private boolean dying;
    private CreatureState currentState = CreatureState.IDLE;

    /**
     * @param group
     *            The @Group in which the entity view is added.
     * @param dimension
     *            The entity view dimension.
     * @param audioplayer
     *            The audio player.
     */
    public AbstractLivingEntityView(final Group group, final Dimension2D dimension, final AudioPlayer audioplayer) {
        super(group, dimension);
        this.audioplayer = audioplayer;
    }

    /**
     * {@inheritDoc}.
     * The currentState is updated.
     */
    @Override
    public void changeState(final CreatureState state) {
        currentState = state;
        super.changeState(state);
    }

    @Override
    public final void changeFaceDirection(final HorizontalDirection direction) {
        getView().setScaleX(direction == HorizontalDirection.RIGHT ? 1 : -1);
    }

    @Override
    public final void deathUpdate() {
        if (!dying) {
            audioplayer.playSFX(SFX.DEATH.getPath());
            deathAnimation();
            dying = true;
        }
        manageDespawn();
    }

    /**
     * Used to make a pain animation.
     * 
     * @param image
     *            The image containing the frame.
     * @param duration
     *            The duration in seconds of the frame.
     * @param audioPath
     *            The string containing the audio path of the associated sound effect.
     * @return A @Runnable animation.
     */
    protected Runnable painAnimation(final Image image, final int duration, final String audioPath) {
        return () -> {
            audioplayer.playSFX(audioPath);
            setImage(image);
            final Timeline anim = new Timeline(
                    new KeyFrame(Duration.millis(duration), e -> {
                        currentState = CreatureState.IDLE;
                        changeState(CreatureState.IDLE);
                    }));
            setAnimation(anim);
        };
    }

    /**
     * 
     * @return the current @CreatureState.
     */
    protected CreatureState getCurrentState() {
        return currentState;
    }

    private void manageDespawn() {
        deathTimeCount++;
        if (deathTimeCount == DEATH_TIME) {
            remove();
        }
    }

    private void deathAnimation() {
        final TranslateTransition deathTransition = new TranslateTransition(Duration.millis(1000), getView());
        deathTransition.setCycleCount(1);
        deathTransition.setByY(100);
        deathTransition.setRate(DEATH_FALLING_RATE_SPEED);
        deathTransition.play();
        getView().setScaleY(-1);
    }

}
