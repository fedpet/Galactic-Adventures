package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.ContactTrigger;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class Lever extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 0.8);

    public Lever(final BodyFactory bodyFactory, final Point2D position, final String password,
            final boolean activated) {
        super(bodyFactory.createItem(position, SIZE));
        add(new ContactTrigger(password, activated));
    }

    @Override
    public String toString() {
        return "Lever";
    }

}