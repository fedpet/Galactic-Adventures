package it.unibo.oop17.ga_game.model.entities;

import static com.google.common.base.Predicates.equalTo;
import static com.google.common.base.Predicates.not;

import java.util.function.Supplier;

import it.unibo.oop17.ga_game.model.entities.components.EntityPersonality;
import it.unibo.oop17.ga_game.model.entities.components.FixedPatternPilot;
import it.unibo.oop17.ga_game.model.entities.components.LinearLife;
import it.unibo.oop17.ga_game.model.entities.components.LinearPropeller;
import it.unibo.oop17.ga_game.model.entities.components.MeleeWeapon;
import it.unibo.oop17.ga_game.model.entities.components.ViolentBrain;
import it.unibo.oop17.ga_game.model.physics.BodyBuilder;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * Models a flying enemy entity.
 */
public class FlyingEnemy extends AbstractEntity {
    private static final int DEFAULT_LIFE = 2;
    private static final Dimension2D SIZE = new Dimension2D(1, 1.5);
    private static final double ATTACK_KNOCKBACK = 30;
    private static final double FLYING_SPEED = 5;


    /**
     * 
     * @param bodyBuilder
     *            the related {@link BodyBuilder} object.
     * @param position
     *            Its position (relative to its center).
     * @param positions
     *            The path the entity has to follow.
     */
    public FlyingEnemy(final BodyBuilder bodyBuilder, final Point2D position, final Supplier<Point2D> positions) {
        super(bodyBuilder
                .position(position)
                .size(SIZE)
                .build());
        add(new ViolentBrain(EntityPersonality.EVIL));
        add(new FixedPatternPilot(positions));
        add(new LinearPropeller(FLYING_SPEED));
        add(new LinearLife(DEFAULT_LIFE));
        add(new MeleeWeapon(1, 0, ATTACK_KNOCKBACK, not(equalTo(Side.TOP))));
    }

    @Override
    public final String toString() {
        return "Flying enemy";
    }

}
