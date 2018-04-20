package it.unibo.oop17.ga_game.view;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Controls the sprite animation.
 * 
 * From https://netopyr.com/2012/03/09/creating-a-sprite-animation-with-javafx/
 */
public final class SpriteAnimation extends Transition {
    private final ImageView imageView;
    private final int count;
    private final double width;
    private final double height;

    private int lastIndex;

    /**
     * Constructor for sprite animation.
     * @param imageView
     *          The image view.
     * @param duration
     *          The duration.
     * @param count
     *          The count.
     * @param width
     *          The width.
     * @param height
     *          The height.
     */
    public SpriteAnimation(final ImageView imageView, final Duration duration, final int count, final double width,
            final double height) {
        super();
        this.imageView = imageView;
        this.count = count;
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
            final double x = cols == 0 ? 0 : index % cols * width;
            final double y = rows == 0 ? 0 : index % rows * height;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}
