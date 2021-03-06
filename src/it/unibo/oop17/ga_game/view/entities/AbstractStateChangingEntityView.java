package it.unibo.oop17.ga_game.view.entities;

import java.util.HashMap;
import java.util.Map;

import it.unibo.oop17.ga_game.model.entities.components.EntityState;
import it.unibo.oop17.ga_game.view.SpriteAnimation;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Base class for {@link StateChangingEntityView}.
 * 
 * @param <S>
 *            {@link EntityState} type.
 */
public abstract class AbstractStateChangingEntityView<S extends EntityState> extends AbstractEntityView
        implements StateChangingEntityView<S> {

    private final Map<S, Runnable> animations = new HashMap<>();
    private Animation currentAnimation;

    /**
     * @param group
     *            The {@link Group} instance in which the entity view is added.
     * @param dimension
     *            The entity view dimension.
     */
    public AbstractStateChangingEntityView(final Group group, final Dimension2D dimension) {
        super(group, dimension);
        currentAnimation = new Transition() {
            @Override
            protected void interpolate(final double frac) {
                // dummy animation
            }
        };
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public void changeState(final S state) {
        if (animations.containsKey(state)) {
            animations.get(state).run();
        }
    }

    /**
     * {@inheritDoc}
     * The current animation is stopped.
     */
    @Override
    public final void remove() {
        currentAnimation.stop();
        super.remove();
    }

    /**
     * Used to make a looping sprite animation.
     * 
     * @param image
     *            The {@link Image} instance containing the frame animations.
     * @param duration
     *            The {@link Duration} instance defining the seconds of a frame.
     * @param frames
     *            The number of frames used for the animation.
     * @return A {@link Runnable} instance animation.
     */
    protected Runnable aSpriteAnimation(final Image image, final Duration duration, final int frames) {
        return () -> {
            setImage(image);
            final Animation newAnim = new SpriteAnimation(getView(), duration, frames,
                    getDimension().getWidth(),
                    getDimension().getHeight());
            newAnim.setCycleCount(Animation.INDEFINITE);
            setAnimation(newAnim);
        };
    }

    /**
     * Used to make a static sprite animation.
     * 
     * @param image
     *            The sprite {@link Image} instance.
     * @return A {@link Runnable} animation instance.
     */
    protected Runnable justAnImage(final Image image) {
        return () -> {
            setImage(image);
        };
    }

    /**
     * Used to start an animation from the mapped animations.
     * 
     * @param state
     *            The {@link EntityState} instance associated to the specific animation that has to start.
     */
    protected void startAnimation(final S state) {
        currentAnimation.stop();
        animations.get(state).run();
    }

    /**
     * Map an animation for the entity view.
     * 
     * @param state
     *            The {@link EntityState} instance to which the animation has to be associated.
     * @param runnable
     *            The {@link Runnable} animation instance to map.
     */
    protected void mapAnimation(final S state, final Runnable runnable) {
        animations.put(state, runnable);
    }

    /**
     * Used to set an animation for the entity view.
     * 
     * @param animation
     *            The {@Animation} instance to set for the entity view.
     */
    protected void setAnimation(final Animation animation) {
        currentAnimation.stop();
        currentAnimation = animation;
        currentAnimation.play();
    }

    /**
     * Used to set an image for the entity view.
     * 
     * @param image
     *            The {@link Image} instance to set for the entity view.
     */
    protected void setImage(final Image image) {
        getView().setImage(image);
        getView().setViewport(new Rectangle2D(0, 0, getDimension().getWidth(), getDimension().getHeight()));
    }
}
