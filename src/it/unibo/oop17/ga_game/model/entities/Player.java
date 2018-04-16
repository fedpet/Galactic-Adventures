package it.unibo.oop17.ga_game.model.entities;

import static com.google.common.base.Predicates.equalTo;

import it.unibo.oop17.ga_game.model.entities.components.EntityPersonality;
import it.unibo.oop17.ga_game.model.entities.components.Feet;
import it.unibo.oop17.ga_game.model.entities.components.InventoryImpl;
import it.unibo.oop17.ga_game.model.entities.components.LinearLife;
import it.unibo.oop17.ga_game.model.entities.components.MeleeWeapon;
import it.unibo.oop17.ga_game.model.entities.components.ViolentBrain;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * Models our player.
 */
public final class Player extends AbstractEntity {
    private static final int DEFAULT_LIFE = 5;
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);
    private static final double WALK_SPEED = 10;
    private static final double JUMP_SPEED = 22;

    /**
     * 
     * @param bodyBuilder
     *            the @BodyBuilder.
     * @param position
     *            Its position (relative to its center).
     */
    public Player(final BodyBuilder bodyBuilder, final Point2D position) {
        super(bodyBuilder
                .position(position)
                .size(SIZE)
                .build());
        add(new InventoryImpl());
        add(new LinearLife(DEFAULT_LIFE));
        add(new Feet(WALK_SPEED, JUMP_SPEED));
        add(new ViolentBrain(EntityPersonality.GOOD));
        add(new MeleeWeapon(1, JUMP_SPEED, 0, equalTo(Side.BOTTOM)));
    }

    @Override
    public String toString() {
        return "Player";
    }
}
