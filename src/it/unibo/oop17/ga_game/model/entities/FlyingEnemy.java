package it.unibo.oop17.ga_game.model.entities;

import java.util.function.Supplier;

import it.unibo.oop17.ga_game.model.entities.components.EntityPersonality;
import it.unibo.oop17.ga_game.model.entities.components.FixedPatternPilot;
import it.unibo.oop17.ga_game.model.entities.components.LinearLife;
import it.unibo.oop17.ga_game.model.entities.components.LinearPropeller;
import it.unibo.oop17.ga_game.model.entities.components.MeleeWeapon;
import it.unibo.oop17.ga_game.model.entities.components.ViolentBrain;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class FlyingEnemy extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);
    private static final double ATTACK_KNOCKBACK = 30;

    /**
     * 
     * @param world
     *            The world in which to spawn the player.
     * @param position
     *            The position
     */
    public FlyingEnemy(final BodyFactory bodyFactory, final Point2D position, final Supplier<Point2D> positions) {
        super(bodyFactory.createCreature(position, SIZE));
        add(new ViolentBrain(EntityPersonality.EVIL));
        add(new FixedPatternPilot(positions));
        add(new LinearPropeller(5));
        add(new LinearLife(5));
        getBody().setGravityScale(0);
        add(new MeleeWeapon(1, 0, ATTACK_KNOCKBACK, PositionCompare::exceptBottom));
    }

    @Override
    public String toString() {
        return "Flying enemy";
    }

}