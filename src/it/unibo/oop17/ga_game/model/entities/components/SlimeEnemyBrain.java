package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public class SlimeEnemyBrain extends AbstractEntityComponent implements Brain {

    @Override
    public void beginContact(final EntityBody other) {

        if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.LEFT)) {
            getEntity().getMovement().move(new Point2D(1, 0));
        } else if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.RIGHT)) {
            getEntity().getMovement().move(new Point2D(-1, 0));
        }

    }

    @Override
    public void endContact(final EntityBody other) {

    }

    @Override
    public void update(final double dt) {
        if (getEntity().getMovement().getFaceDirection() == HorizontalDirection.RIGHT) {
            getEntity().getMovement().move(new Point2D(1, 0));
        } else {
            getEntity().getMovement().move(new Point2D(-1, 0));
        }
    }

}
