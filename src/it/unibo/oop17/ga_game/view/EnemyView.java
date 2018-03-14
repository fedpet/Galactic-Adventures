package it.unibo.oop17.ga_game.view;

import java.util.HashMap;
import java.util.Map;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import it.unibo.oop17.ga_game.model.entities.events.EntityEventListener;
import it.unibo.oop17.ga_game.model.entities.events.FaceDirectionEvent;
import it.unibo.oop17.ga_game.model.entities.events.MovementEvent;
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

public abstract class EnemyView implements EntityEventListener {

    private final Dimension2D dimension;
    private final Map<MovementComponent.State, Runnable> animations = new HashMap<>();
    private final ImageView view = new ImageView();
    private Animation currentAnimation;

    public EnemyView(final Group group, int width, int height, Image image) {
        this.dimension = new Dimension2D(width, height);
        currentAnimation = new Transition() {
            @Override
            protected void interpolate(final double frac) {
                // dummy animation
            }
        };

        animations.put(State.WALKING, setAnimation(image, Duration.millis(700), 2));

        animations.get(State.WALKING).run();

        group.getChildren().add(view);

    }

    public void setPosition(final Point2D point) {
        view.setTranslateX(point.getX() - view.getBoundsInLocal().getWidth() / 2);
        view.setTranslateY(point.getY() - view.getBoundsInLocal().getHeight() / 2);
    }

    public Point2D getPosition() {
        return new Point2D(view.getTranslateX(), view.getTranslateY());
    }

    public void setDimension(final Dimension2D dimension) {
        view.setFitWidth(ViewUtils.metersToPixels(dimension.getWidth()));
        view.setFitHeight(ViewUtils.metersToPixels(dimension.getHeight()));
    }

    private Runnable setAnimation(final Image image, final Duration duration, final int frames) {
        return () -> {
            setImage(image);
            currentAnimation.stop();
            currentAnimation = new SpriteAnimation(view, duration, frames, 0, 0, (int) dimension.getWidth(),
                    (int) dimension.getHeight());
            currentAnimation.setCycleCount(Animation.INDEFINITE);
            currentAnimation.play();
        };
    }

    private Runnable justAnImage(final Image image) {
        return () -> {
            setImage(image);
        };
    }

    private void setImage(final Image image) {
        currentAnimation.stop();
        view.setImage(image);
        view.setViewport(new Rectangle2D(0, 0, dimension.getWidth(), dimension.getHeight()));
    }

    @Subscribe
    public void movementChanged(final MovementEvent event) {
        animations.getOrDefault(event.getState(), animations.get(State.WALKING)).run();
    }

    @Subscribe
    public void faceDirectionChanged(final FaceDirectionEvent event) {
        view.setScaleX(event.getDirection() == HorizontalDirection.RIGHT ? -1 : 1);
    }

}
