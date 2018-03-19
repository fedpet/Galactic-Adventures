package it.unibo.oop17.ga_game.model.entities.components;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;
import javafx.geometry.Point2D;

public class PlayerBrain extends AbstractBrain {
    private static final double KNOCKBACK = 60;

    @Override
    public void update(final double dt) {

    }

    @Subscribe
    public void beginContact(final BeginContactEvent contact) {
        contact.getOtherBody().getOwner().ifPresent(otherEntity -> {
            if (hate(otherEntity)
                    && contact.getPoint().getY() <= -getEntity().getBody().getDimension().getHeight() / 2) {
                otherEntity.get(Life.class).ifPresent(life -> {
                    life.hurt(1);
                });
                getEntity().getBody().applyImpulse(new Point2D(0, KNOCKBACK));
            }
        }); 
    }

    @Override
    public final Personality getPersonality() {
        return EntityPersonality.GOOD;
    }
}
