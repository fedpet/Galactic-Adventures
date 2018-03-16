package it.unibo.oop17.ga_game.model.entities.components;

import java.util.function.Supplier;

import javafx.geometry.Point2D;

/**
 * A @Brain following a fixed pattern.
 */
public abstract class FixedPatternBrain extends AbstractBrain {
    private static final double DISTANCE_THRESHOLD = 0.1;
    private final Supplier<Point2D> nextPositionSupplier;
    private Point2D nextPosition;

    /**
     * @param nextPositionSupplier
     *            A supplier of the positions to follow.
     */
    public FixedPatternBrain(final Supplier<Point2D> nextPositionSupplier) {
        this.nextPositionSupplier = nextPositionSupplier;
        nextPosition = nextPositionSupplier.get();
    }

    /**
     * Follows the pattern.
     */
    @Override
    public void update(final double dt) {
        followPattern();
    }

    @Override
    public void beginContact(final EntityBody other) {
        // does nothing
    }

    @Override
    public void endContact(final EntityBody other) {
        // does nothing
    }

    /**
     * Follows the pattern.
     */
    protected final void followPattern() {
        if (getEntity().getBody().getPosition().distance(nextPosition) < DISTANCE_THRESHOLD) {
            nextPosition = nextPositionSupplier.get();
        }
        final Point2D direction = nextPosition.subtract(getEntity().getBody().getPosition());
        getEntity().getMovement().move(direction);
    }
}
