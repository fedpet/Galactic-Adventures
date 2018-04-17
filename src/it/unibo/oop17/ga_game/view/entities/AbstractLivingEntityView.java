package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.view.AudioPlayer;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.animation.TranslateTransition;
import javafx.geometry.Dimension2D;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.VerticalDirection;
import javafx.scene.Group;
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

    @Override
    public final void changeFaceDirection(final HorizontalDirection direction) {
        getView().setScaleX(direction == HorizontalDirection.RIGHT ? 1 : -1);
    }

    @Override
    public final void deathAnimation() {
        if (!dying) {
            final TranslateTransition deathTransition = new TranslateTransition(Duration.millis(1000), getView());
            deathTransition.setCycleCount(1);
            deathTransition.setByY(100);
            deathTransition.setRate(DEATH_FALLING_RATE_SPEED);
            deathTransition.play();
            audioplayer.playSFX(SFX.DEATH.getPath());
            flip(VerticalDirection.DOWN);
            dying = true;
        }

        manageDespawn();
    }

    private void manageDespawn() {
        deathTimeCount++;
        if (deathTimeCount == DEATH_TIME) {
            remove();
        }
    }

    private void flip(final VerticalDirection direction) {
        getView().setScaleY(direction == VerticalDirection.UP ? 1 : -1);
    }

}
