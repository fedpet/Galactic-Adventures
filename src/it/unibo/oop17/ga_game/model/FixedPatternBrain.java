package it.unibo.oop17.ga_game.model;

import java.util.function.Supplier;

import javafx.geometry.Point2D;

public class FixedPatternBrain extends AbstractBrain {
    private final Supplier<Point2D> nextPositionSupplier;
    private Point2D nextPosition;

    public FixedPatternBrain(final Supplier<Point2D> nextPositionSupplier) {
        this.nextPositionSupplier = nextPositionSupplier;
        nextPosition = nextPositionSupplier.get();
    }

    @Override
    public void update(final double dt) {
        if (getEntity().getBody().getPosition().distance(nextPosition) < 0.1) {
            nextPosition = nextPositionSupplier.get();
        }
        final Point2D direction = nextPosition.subtract(getEntity().getBody().getPosition());
        getEntity().getMovement().move(direction);
    }

    @Override
    public void beginContact(final EntityBody other) {
        // does nothing
    }

    @Override
    public void endContact(final EntityBody other) {
        // does nothing
    }
}
