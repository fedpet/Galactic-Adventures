package it.unibo.oop17.ga_game.model;

import java.util.function.Supplier;

import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class MovingPlatform extends AbstractEntity {
    private static final double SPEED = 2;

    public MovingPlatform(final PhysicsEngine engine, final Point2D position, final Dimension2D size,
            final Supplier<Point2D> positions) {
        super(engine.bodyFactory().createMovingPlatform(position, size), new FixedPatternBrain(positions),
                new LinearPropeller(SPEED));
        getBody().setGravityScale(0);
    }
}