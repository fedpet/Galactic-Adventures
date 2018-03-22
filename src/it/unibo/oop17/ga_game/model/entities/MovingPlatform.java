package it.unibo.oop17.ga_game.model.entities;

import java.util.function.Supplier;

import it.unibo.oop17.ga_game.model.entities.components.FixedPatternPilot;
import it.unibo.oop17.ga_game.model.entities.components.LinearPropeller;
import it.unibo.oop17.ga_game.model.entities.components.PlatformBrain;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class MovingPlatform extends AbstractEntity {
    private static final double SPEED = 2;

    public MovingPlatform(final BodyFactory bodyFactory, final Point2D position, final Dimension2D size,
            final Supplier<Point2D> positions) {
        super(bodyFactory.createMovingPlatform(position, size));
        add(new LinearPropeller(SPEED));
        add(new PlatformBrain());
        add(new FixedPatternPilot(positions));
    }

}