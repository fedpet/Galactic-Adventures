package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.physics.BodyContact;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public class SlimeEnemyBrain extends AbstractBrain {

    @Override
    public void beginContact(final BodyContact contact) {
        if (PositionCompare.contact(getEntity().getBody(), contact.getOtherBody()).equals(Side.LEFT)) {
            getEntity().getMovement().move(new Point2D(1, 0));
        } else if (PositionCompare.contact(getEntity().getBody(), contact.getOtherBody()).equals(Side.RIGHT)) {
            getEntity().getMovement().move(new Point2D(-1, 0));
        }

    }

    @Override
    public void update(final double dt) {
        if (getEntity().getMovement().getFaceDirection() == HorizontalDirection.RIGHT) {
            getEntity().getMovement().move(new Point2D(1, 0));
        } else {
            getEntity().getMovement().move(new Point2D(-1, 0));
        }
    }

    @Override
    public final Personality getPersonality() {
        return EntityPersonality.EVIL;
    }
}
