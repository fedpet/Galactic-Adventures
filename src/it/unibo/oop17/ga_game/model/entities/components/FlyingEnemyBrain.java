package it.unibo.oop17.ga_game.model.entities.components;

import java.util.function.Supplier;

import javafx.geometry.Point2D;

public class FlyingEnemyBrain extends FixedPatternBrain {

    public FlyingEnemyBrain(Supplier<Point2D> nextPositionSupplier) {
        super(nextPositionSupplier);
    }

    @Override
    public void beginContact(final EntityBody other) {

    }


    @Override
    public void endContact(final EntityBody other) {

    }

}