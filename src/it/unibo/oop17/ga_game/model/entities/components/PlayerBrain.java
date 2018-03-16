package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.physics.BodyContact;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public class PlayerBrain extends AbstractBrain {
    @Override
    public void update(final double dt) {

    }

    @Override
    public void beginContact(final BodyContact contact) {
        contact.getOtherBody().getOwner().ifPresent(otherEntity -> {
            if (PositionCompare.contact(getEntity().getBody(), contact.getOtherBody()) == Side.BOTTOM && hate(otherEntity)) {
                otherEntity.getLife().hurt(1);
                getEntity().getBody().applyImpulse(new Point2D(0, 20));
            }
        }); 
    }

    @Override
    public final Personality getPersonality() {
        return EntityPersonality.GOOD;
    }
}
