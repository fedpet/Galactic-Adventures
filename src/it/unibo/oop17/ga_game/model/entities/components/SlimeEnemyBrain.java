package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;
import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;

public class SlimeEnemyBrain extends ViolentBrain {

    public SlimeEnemyBrain() {
        super(EntityPersonality.EVIL);
    }

    @Override
    public void beginContact(final BeginContactEvent contact) {
        super.beginContact(contact);
        final Point2D newDirection = PositionCompare
                .sideToDirection(PositionCompare.relativeSide(getEntity().getBody(), contact.getOtherBody()));

        final Point2D dir = new Point2D(newDirection.getX(), 0);
        if (!newDirection.equals(Point2D.ZERO)) {
            getEntity().get(MovementComponent.class).ifPresent(movement -> {
                movement.move(dir.multiply(-1));
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
}
