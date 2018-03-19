package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.entities.components.KeyBrain;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class Key extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 0.8);

    public Key(final BodyFactory bodyFactory, final Point2D position,
            final KeyLockType type) {
        super(bodyFactory.createItem(position, SIZE));
        add(new KeyBrain(type));
    }

    @Override
    public String toString() {
        return "Key";
    }

}