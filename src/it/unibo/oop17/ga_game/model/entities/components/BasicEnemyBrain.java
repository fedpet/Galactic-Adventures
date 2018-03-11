package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Point2D;
import javafx.geometry.Side;

public class BasicEnemyBrain extends AbstractEntityComponent implements Brain {

    private final Point2D desiredMovement = Point2D.ZERO;
    private static final float WALK_SPEED = 5f;
    private static final float JUMP_SPEED = 0f;

    @Override
    public void beginContact(final EntityBody other) {

        /*
         * in teoria dovrebbe cambiare direzione se il rettangolo di other contiene:
         * un punto leggermente distante dal vertice più in basso a destra dell'owner
         * un punto leggermente distante dal vertice più in basso a sinistra dell'owner
         */

        if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.LEFT)) {
            // move(HorizontalDirection.RIGHT);
        } else if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.RIGHT)) {
            // move(HorizontalDirection.LEFT);
        }

    }

    @Override
    public void endContact(final EntityBody other) {

    }

    @Override
    public void update(final double dt) {

    }

}
