package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.physics.BodyContact;
import javafx.geometry.Point2D;

public class PlayerBrain extends AbstractBrain {
    private static final double KNOCKBACK = 60;

    @Override
    public void update(final double dt) {

    }

    @Override
    public void beginContact(final BodyContact contact) {
        contact.getOtherBody().getOwner().ifPresent(otherEntity -> {
            if (hate(otherEntity)
                    && contact.getPoint().getY() <= -getEntity().getBody().getDimension().getHeight() / 2) {
                otherEntity.getLife().hurt(1);
                getEntity().getBody().applyImpulse(new Point2D(0, KNOCKBACK));
            }
        }); 
    }

    @Override
    public final Personality getPersonality() {
        return EntityPersonality.GOOD;
    }
}
