package it.unibo.oop17.ga_game.model.entities.components;

import java.util.function.Supplier;

import javafx.geometry.Point2D;

public class FlyingEnemyBrain extends FixedPatternBrain {

    public FlyingEnemyBrain(final Supplier<Point2D> nextPositionSupplier) {
        super(nextPositionSupplier);
    }

    @Override
    public Personality getPersonality() {
        return EntityPersonality.EVIL;
    }
}