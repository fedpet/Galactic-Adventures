package it.unibo.oop17.ga_game.model.entities;

import static com.google.common.base.Predicates.equalTo;

import it.unibo.oop17.ga_game.model.entities.components.EntityPersonality;
import it.unibo.oop17.ga_game.model.entities.components.MeleeWeapon;
import it.unibo.oop17.ga_game.model.entities.components.ViolentBrain;
import it.unibo.oop17.ga_game.model.entities.events.TriggeringEvent;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

/**
 * A jumping platform makes entities jump when they touch its TOP.
 */
public class JumpingPlatform extends AbstractEntity {
    private static final Dimension2D SIZE = new Dimension2D(1, 0.3);
    private static final double JUMP_FORCE = 40;

    /**
     * 
     * @param bodyFactory
     *            the @BodyFactory.
     * @param position
     *            Its position (relative to its center).
     */
    public JumpingPlatform(final BodyFactory bodyFactory, final Point2D position) {
        super(bodyFactory.createStatic(position, SIZE));
        add(new ViolentBrain(EntityPersonality.PSYCHO));
        add(new MeleeWeapon(0, 0, JUMP_FORCE, equalTo(Side.TOP)));
    }

    @Override
    public void update(final double dt) {
        super.update(dt);
        getBody().getContacts()
                .filter(body -> PositionCompare.relativeSide(getBody(), body) == Side.TOP)
                .findAny()
                .ifPresent(b -> {
                    post(new TriggeringEvent(this));
                });
    }
}
