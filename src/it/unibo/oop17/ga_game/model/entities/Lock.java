package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.entities.components.DeadMovement;
import it.unibo.oop17.ga_game.model.entities.components.InvincibleLife;
import it.unibo.oop17.ga_game.model.entities.components.LockBrain;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class Lock extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 0.8);

    public Lock(final BodyFactory bodyFactory, final Point2D position,
            final KeyLockType type) {
        super(bodyFactory.createTerrain(position, SIZE), new LockBrain(type),
                new DeadMovement(), new InvincibleLife());
    }

    @Override
    public String toString() {
        return "Lock";
    }

}