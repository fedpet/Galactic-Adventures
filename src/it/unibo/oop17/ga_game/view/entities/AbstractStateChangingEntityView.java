package it.unibo.oop17.ga_game.view.entities;

import java.util.HashMap;
import java.util.Map;

import it.unibo.oop17.ga_game.model.entities.components.GenericState;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public abstract class AbstractStateChangingEntityView<S extends GenericState> extends AbstractEntityView
        implements StateChangingEntityView<S> {

    private final Map<S, Runnable> animations = new HashMap<>();
    private Animation currentAnimation;

    public AbstractStateChangingEntityView(final Group group, final Dimension2D dimension) {
        super(group, dimension);
        currentAnimation = new Transition() {
            @Override
            protected void interpolate(final double frac) {
                // dummy animation
            }
        };
    }
    
    @Override
    public void changeState(final S state) {
        if (animations.containsKey(state)) {
            animations.get(state).run();
        }
    }

    @Override
    public void remove() {
        currentAnimation.stop();
        super.remove();
    }

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

    protected Runnable justAnImage(final Image image) {
        return () -> {
            setImage(image);
        };
    }

    protected void startAnimation(final S state) {
        currentAnimation.stop();
        animations.get(state).run();
    }

    protected void mapAnimation(final S state, final Runnable runnable) {
        animations.put(state, runnable);
    }

    protected void setAnimation(final Animation animation) {
        currentAnimation.stop();
        currentAnimation = animation;
        currentAnimation.play();
    }

    protected void setImage(final Image image) {
        getView().setImage(image);
        getView().setViewport(new Rectangle2D(0, 0, getDimension().getWidth(), getDimension().getHeight()));
    }
}
