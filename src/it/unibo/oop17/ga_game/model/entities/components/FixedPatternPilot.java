package it.unibo.oop17.ga_game.model.entities.components;

import java.util.function.Supplier;

import javafx.geometry.Point2D;

/**
 * A @AutoPilot following a fixed pattern.
 */
public class FixedPatternPilot extends AbstractEntityComponent implements AutoPilot {
    private static final double DISTANCE_THRESHOLD = 0.1;
    private final Supplier<Point2D> nextPositionSupplier;
    private Point2D nextPosition;

    /**
     * @param nextPositionSupplier
     *            A supplier of the positions to follow.
     */
    public FixedPatternPilot(final Supplier<Point2D> nextPositionSupplier) {
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

    /**
     * Follows the pattern.
     */
    protected final void followPattern() {
        getEntity().get(MovementComponent.class).ifPresent(movement -> {
            if (getEntity().getBody().getPosition().distance(nextPosition) < DISTANCE_THRESHOLD) {
                nextPosition = nextPositionSupplier.get();
            }
            final Point2D direction = nextPosition.subtract(getEntity().getBody().getPosition());
            movement.move(direction);
        });
    }
}
