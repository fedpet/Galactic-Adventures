package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class FlyingEnemy extends AbstractEntity implements Entity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);

    public FlyingEnemy(final PhysicsEngine engine, final Point2D position) {
        super(engine.bodyFactory().createCreature(position, SIZE, 1000), new FlyingEnemyBrain());
        getBody().setGravityScale(0);
    }

    @Override
    public void update(double dt) {
        getBrain().update(dt);
    }

}
