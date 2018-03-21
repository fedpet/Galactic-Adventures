package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.OneTimeTriggerable;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class Door extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);

    public Door(final BodyFactory bodyFactory, final Point2D position, String password, boolean open) {
        super(bodyFactory.createItem(position, SIZE));
        add(new OneTimeTriggerable(password, open));
    }

    @Override
    public String toString() {
        return "Door";
    }

}