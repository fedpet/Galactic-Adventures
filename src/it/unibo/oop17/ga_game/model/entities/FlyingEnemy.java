package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.FlyingEnemyBrain;
import it.unibo.oop17.ga_game.model.entities.components.PropellerComponent;
import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class FlyingEnemy extends AbstractEnemy {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);


    /**
     * 
     * @param world
     *            The world in which to spawn the player.
     * @param position
     *            The position
     */
    public FlyingEnemy(final PhysicsEngine engine, final Point2D position) {
        super(engine.bodyFactory().createCreature(position, SIZE), new FlyingEnemyBrain(),
                new PropellerComponent(5), 1);
        getBody().setGravityScale(0);
    }

    @Override
    public String toString() {
        return "Flying enemy";
    }


}
