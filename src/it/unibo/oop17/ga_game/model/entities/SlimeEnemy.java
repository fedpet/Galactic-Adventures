package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.FeetComponent;
import it.unibo.oop17.ga_game.model.entities.components.LinearLife;
import it.unibo.oop17.ga_game.model.entities.components.MeleeWeapon;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.components.SlimeEnemyBrain;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * Models an enemy that switches moving direction when collides against an
 * obstacle.
 */
public final class SlimeEnemy extends AbstractEntity {
    private static final int DEFAULT_LIFE = 5;
    private static final Dimension2D SIZE = new Dimension2D(0.8, 0.8);
    private static final double ATTACK_KNOCKBACK = 20;
    private static final double WALK_SPEED = 5;
    private static final double JUMP_SPEED = 0;

    /**
     * 
     * @param bodyBuilder
     *            the @BodyBuilder.
     * @param position
     *            Its position (relative to its center).
     */
    public SlimeEnemy(final BodyBuilder bodyBuilder, final Point2D position) {
        super(bodyBuilder
                .position(position)
                .size(SIZE)
                .build());
        add(new SlimeEnemyBrain());
        add(new FeetComponent(WALK_SPEED, JUMP_SPEED));
        add(new LinearLife(DEFAULT_LIFE));
        get(MovementComponent.class).ifPresent(movement -> {
            movement.move(new Point2D(1, 0));
        });
        add(new MeleeWeapon(1, 0, ATTACK_KNOCKBACK, Side::isVertical));
    }

    @Override
    public String toString() {
        return "Slime enemy";
    }
}
