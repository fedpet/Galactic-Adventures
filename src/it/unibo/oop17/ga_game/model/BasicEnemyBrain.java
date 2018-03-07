package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.HorizontalDirection;

public class BasicEnemyBrain extends AbstractEntityComponent implements Brain {

    @Override
    public void beginContact(EntityBody other) {

        /*
         * in teoria dovrebbe cambiare direzione se il rettangolo di other contiene:
         * un punto leggermente distante dal vertice più in basso a destra dell'owner
         * un punto leggermente distante dal vertice più in basso a sinistra dell'owner
         */
        if ((PositionCompare.contactLeft(getEntity().getBody(), other)
                || PositionCompare.contactRight(getEntity().getBody(), other))
                && getEntity() instanceof BasicEnemy) {
            final BasicEnemy e = (BasicEnemy) getEntity();
            if (e.getMovingDirection().equals(HorizontalDirection.LEFT)) {
                // System.out.println("right");
                e.move(HorizontalDirection.RIGHT);
                } else {
                // System.out.println("left");
                e.move(HorizontalDirection.LEFT);
                }
        }

    }

    @Override
    public void endContact(EntityBody other) {

    }

}
