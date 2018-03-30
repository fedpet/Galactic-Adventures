package it.unibo.oop17.ga_game.model.entities;

import static com.google.common.base.Predicates.alwaysTrue;

import it.unibo.oop17.ga_game.model.entities.components.EntityPersonality;
import it.unibo.oop17.ga_game.model.entities.components.MeleeWeapon;
import it.unibo.oop17.ga_game.model.entities.components.ViolentBrain;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

public class Spikes extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(0.8, 1.5);
    private static final double ATTACK_KNOCKBACK = 20;

    /**
     * 
     * @param world
     *            The world in which to spawn the player.
     * @param position
     *            The position
     */
    public Spikes(final BodyFactory bodyFactory, final Point2D position) {
        super(bodyFactory.createStatic(position, SIZE));
        add(new ViolentBrain(EntityPersonality.EVIL));
        add(new MeleeWeapon(1, 0, ATTACK_KNOCKBACK, alwaysTrue()));
    }

    @Override
    public String toString() {
        return "Spikes";
    }

}