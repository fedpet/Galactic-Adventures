package it.unibo.oop17.ga_game.model.entities;

import java.util.function.Supplier;

import it.unibo.oop17.ga_game.model.entities.components.InvincibleLife;
import it.unibo.oop17.ga_game.model.entities.components.LinearPropeller;
import it.unibo.oop17.ga_game.model.entities.components.PlatformBrain;
import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class MovingPlatform extends AbstractEntity {
    private static final double SPEED = 2;

    public MovingPlatform(final PhysicsEngine engine, final Point2D position, final Dimension2D size,
            final Supplier<Point2D> positions) {
        super(engine.bodyFactory().createMovingPlatform(position, size), new PlatformBrain(positions),
                new LinearPropeller(SPEED), new InvincibleLife());
    }

}