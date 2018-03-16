package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public class PlayerBrain extends AbstractBrain {
    @Override
    public void update(final double dt) {

    }

    @Override
    public void beginContact(final EntityBody other) {
        other.getOwner().ifPresent(otherEntity -> {
            if (PositionCompare.contact(getEntity().getBody(), other) == Side.BOTTOM && hate(otherEntity)) {
                otherEntity.getLife().hurt(1);
                getEntity().getBody().applyImpulse(new Point2D(0, 20));
            }
        });
    }

    @Override
    public void endContact(final EntityBody other) {

    }

    @Override
    public final Personality getPersonality() {
        return EntityPersonality.GOOD;
    }
}
