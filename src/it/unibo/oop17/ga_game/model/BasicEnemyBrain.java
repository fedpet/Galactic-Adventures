package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Side;

public class BasicEnemyBrain extends AbstractEntityComponent implements Brain {

    @Override
    public void beginContact(EntityBody other) {

        /*
         * in teoria dovrebbe cambiare direzione se il rettangolo di other contiene:
         * un punto leggermente distante dal vertice più in basso a destra dell'owner
         * un punto leggermente distante dal vertice più in basso a sinistra dell'owner
         */
        if (getEntity() instanceof BasicEnemy) {
            final BasicEnemy e = (BasicEnemy) getEntity();
            if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.LEFT)) {
                e.move(HorizontalDirection.RIGHT);
            } else if (PositionCompare.contact(getEntity().getBody(), other).equals(Side.RIGHT)) {
                e.move(HorizontalDirection.LEFT);
            }
        }

    }

    @Override
    public void endContact(EntityBody other) {

    }

}
