package it.unibo.oop17.ga_game.model.entities;

import java.util.function.Supplier;

import it.unibo.oop17.ga_game.model.entities.components.FixedPatternPilot;
import it.unibo.oop17.ga_game.model.entities.components.LinearPropeller;
import it.unibo.oop17.ga_game.model.entities.components.PlatformBrain;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * A moving platform where entities can stand on.
 */
public class MovingPlatform extends AbstractEntity {
    private static final double SPEED = 2;

    /**
     * 
     * @param bodyBuilder
     *            the related {@link BodyBuilder} object.
     * @param position
     *            Its position (relative to its center).
     * @param size
     *            Its size.
     * @param positions
     *            The path the entity has to follow.
     */
    public MovingPlatform(final BodyBuilder bodyBuilder, final Point2D position, final Dimension2D size,
            final Supplier<Point2D> positions) {
        super(bodyBuilder
                .position(position)
                .size(size)
                .subjectToForces(false)
                .build());
        add(new LinearPropeller(SPEED));
        add(new PlatformBrain());
        add(new FixedPatternPilot(positions));
    }

    @Override
    public final String toString() {
        return "Moving platform";
    }

}
