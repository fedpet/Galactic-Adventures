package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.BasicEnemyBrain;
import it.unibo.oop17.ga_game.model.entities.components.FeetComponent;
import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models an enemy that switches moving direction when collides against an
 * obstacle.
 */
public final class BasicEnemy extends AbstractEntity {
    public static final Dimension2D SIZE = new Dimension2D(0.6, 0.6);

    /**
     * 
     * @param world
     *            The world in which to spawn the player.
     * @param position
     *            The position
     */
    public BasicEnemy(final PhysicsEngine engine, final Point2D position) {
        super(engine.bodyFactory().createCreature(position, SIZE), new BasicEnemyBrain(), new FeetComponent(5, 0));
        getMovement().move(new Point2D(1, 0));
    }

    @Override
    public String toString() {
        return "Basic enemy";
    }
}