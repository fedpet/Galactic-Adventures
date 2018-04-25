package it.unibo.oop17.ga_game.model.entities;

import static com.google.common.base.Predicates.equalTo;

import it.unibo.oop17.ga_game.model.Difficulty;
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
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);
    private static final int EASY_H = 5;
    private static final int MEDIUM_H = 3;
    private static final int HARD_H = 1;
    private static final double WALK_SPEED = 10;
    private static final double JUMP_SPEED = 22;

    /**
     * 
     * @param bodyBuilder
     *            the related {@link BodyBuilder} object.
     * @param position
     *            Its position (relative to its center).
     * @param difficulty
     *            Current difficulty.
     */
    public Player(final BodyBuilder bodyBuilder, final Point2D position, final Difficulty difficulty) {
        super(bodyBuilder
                .position(position)
                .size(SIZE)
                .build());
        add(new InventoryImpl());
        add(new LinearLife(setHealth(difficulty)));
        add(new Feet(WALK_SPEED, JUMP_SPEED));
        add(new ViolentBrain(EntityPersonality.GOOD));
        add(new MeleeWeapon(1, JUMP_SPEED, 0, equalTo(Side.BOTTOM)));
    }

    @Override
    public String toString() {
        return "Player";
    }

    private int setHealth(final Difficulty difficulty) {
        switch (difficulty) {
        case HARD:
            return HARD_H;
        case MEDIUM:
            return MEDIUM_H;
        case EASY:
        default:
            return EASY_H;
        }
    }
}
