package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.physics.BodyContact;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public class SlimeEnemyBrain extends AbstractBrain {

    @Override
    public void beginContact(final BodyContact contact) {
        Point2D newDirection = Point2D.ZERO;
        if (PositionCompare.contact(getEntity().getBody(), contact.getOtherBody()).equals(Side.LEFT)) {
            newDirection = new Point2D(1, 0);
        } else if (PositionCompare.contact(getEntity().getBody(), contact.getOtherBody()).equals(Side.RIGHT)) {
            newDirection = new Point2D(-1, 0);
        }

        final Point2D dir = newDirection;
        if (!newDirection.equals(Point2D.ZERO)) {
            getEntity().get(MovementComponent.class).ifPresent(movement -> {
                movement.move(dir);
            });
        }
    }

    @Override
    public void update(final double dt) {
        getEntity().get(MovementComponent.class).ifPresent(movement -> {
            if (movement.getFaceDirection() == HorizontalDirection.RIGHT) {
                movement.move(new Point2D(1, 0));
            } else {
                movement.move(new Point2D(-1, 0));
            }
        });
    }

    @Override
    public final Personality getPersonality() {
        return EntityPersonality.EVIL;
    }
}
