package it.unibo.oop17.ga_game.view;

import java.util.HashMap;
import java.util.Map;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.animation.Animation;
import javafx.animation.Transition;
import javafx.geometry.Dimension2D;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class AbstractEntityView implements EntityView {
    private final Map<MovementComponent.State, Runnable> animations = new HashMap<>();
    private final ImageView view = new ImageView();
    private Animation currentAnimation;
    private final Dimension2D dimension;
    private final Group parentView;

    public AbstractEntityView(final Group group, final Dimension2D dimension) {
        parentView = group;
        currentAnimation = new Transition() {
            @Override
            protected void interpolate(final double frac) {
                // dummy animation
            }
        };
        this.dimension = dimension;


        group.getChildren().add(view);
    }

    protected void startAnimation(final MovementComponent.State state) {
        animations.get(state).run();
    }

    protected void mapAnimation(final MovementComponent.State state, final Runnable runnable) {
        animations.put(state, runnable);
    }

    @Override
    public void setPosition(final Point2D point) {
        view.setTranslateX(point.getX() - view.getBoundsInLocal().getWidth() / 2);
        view.setTranslateY(point.getY() - view.getBoundsInLocal().getHeight() / 2);
    }

    @Override
    public Point2D getPosition() {
        return new Point2D(view.getTranslateX(), view.getTranslateY());
    }

    @Override
    public void setDimension(final Dimension2D dimension) {
        view.setFitWidth(ViewUtils.metersToPixels(dimension.getWidth()));
        view.setFitHeight(ViewUtils.metersToPixels(dimension.getHeight()));
    }

    @Override
    public void remove() {
        parentView.getChildren().remove(view);
    }

    @Override
    public void changeMovement(MovementComponent.State state) {
        animations.getOrDefault(state, animations.get(State.IDLE)).run();
    }

    @Override
    public void changeFaceDirection(HorizontalDirection direction) {
        view.setScaleX(direction == HorizontalDirection.RIGHT ? 1 : -1);
    }

    protected Runnable setAnimation(final Image image, final Duration duration, final int frames) {
        return () -> {
            setImage(image);
            currentAnimation.stop();
            currentAnimation = new SpriteAnimation(view, duration, frames, 0, 0, dimension.getWidth(),
                    dimension.getHeight());
            currentAnimation.setCycleCount(Animation.INDEFINITE);
            currentAnimation.play();
        };
    }

    protected Runnable justAnImage(final Image image) {
        return () -> {
            setImage(image);
        };
    }

    private void setImage(final Image image) {
        currentAnimation.stop();
        view.setImage(image);
        view.setViewport(new Rectangle2D(0, 0, dimension.getWidth(), dimension.getHeight()));
    }
}
