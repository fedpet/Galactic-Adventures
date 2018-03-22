package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.FeetComponent;
import it.unibo.oop17.ga_game.model.entities.components.LinearLife;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.components.SlimeEnemyBrain;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Models an enemy that switches moving direction when collides against an
 * obstacle.
 */
public final class SlimeEnemy extends AbstractEntity {
    public static final Dimension2D SIZE = new Dimension2D(0.6, 0.6);

    /**
     * 
     * @param world
     *            The world in which to spawn the player.
     * @param position
     *            The position
     */
    public SlimeEnemy(final BodyFactory bodyFactory, final Point2D position) {
        super(bodyFactory.createCreature(position, SIZE));
        add(new SlimeEnemyBrain());
        add(new FeetComponent(5, 0));
        add(new LinearLife(5));
        get(MovementComponent.class).ifPresent(movement -> {
            movement.move(new Point2D(1, 0));
        });
    }

    @Override
    public String toString() {
        return "Slime enemy";
    }
}