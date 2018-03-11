package it.unibo.oop17.ga_game.model;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Supplier;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * A PlatformBrain follows a pattern and takes care of passengers safety.
 */
public final class PlatformBrain extends FixedPatternBrain {
    private final Set<EntityBody> passengers = new LinkedHashSet<>();

    /**
     * 
     * @param nextPositionSupplier A Supplier for the positions to follow.
     */
    public PlatformBrain(final Supplier<Point2D> nextPositionSupplier) {
        super(nextPositionSupplier);
    }

    @Override
    public void beginContact(final EntityBody other) {
        super.beginContact(other);
        if (PositionCompare.contact(getEntity().getBody(), other) == Side.TOP) {
            passengers.add(other);
        }
    }

    @Override
    public void endContact(final EntityBody other) {
        super.endContact(other);
        passengers.remove(other);
    }

    @Override
    public void update(final double dt) {
        super.update(dt);

        // adjust passengers velocity so they don't fall of the platform.
        passengers.forEach(body -> {
            if (body.getLinearVelocity().getY() <= 0) {
                body.applyImpulse(getEntity().getBody().getLinearVelocity());
            }
        });
    }
}
