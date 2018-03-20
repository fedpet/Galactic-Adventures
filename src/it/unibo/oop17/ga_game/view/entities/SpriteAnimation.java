package it.unibo.oop17.ga_game.view.entities;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {
    private final ImageView imageView;
    private final int count;
    private final double offsetX;
    private final double offsetY;
    private final double width;
    private final double height;

    private int lastIndex;

    public SpriteAnimation(final ImageView imageView, final Duration duration, final int count,
            final double offsetX, final double offsetY, final double width, final double height) {
        this.imageView = imageView;
        this.count = count;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(final double k) {
        if (getStatus() != Status.RUNNING) {
            // sometimes this happens..
            return;
        }
        final int cols = (int) (imageView.getImage().getWidth() / width);
        final int rows = (int) (imageView.getImage().getHeight() / height);

        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final double x = index % cols * width + offsetX;
            final double y = index % rows * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}