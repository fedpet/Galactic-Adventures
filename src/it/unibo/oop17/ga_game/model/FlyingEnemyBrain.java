package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.utils.PositionCompare;
import javafx.geometry.Side;

public class FlyingEnemyBrain extends AbstractEntityComponent implements Brain {

    @Override
    public void beginContact(EntityBody other) {

        /*
         * in fase di movimento, in caso di collisione dovrebbe invertire direzione
         */
        if (PositionCompare.contact(getEntity().getBody(), other)
                && getEntity() instanceof FlyingEnemy) {
            System.out.println("pene");
            final FlyingEnemy e = (FlyingEnemy) getEntity();
            if (e.getMovingDirection().equals(Side.LEFT)) {
                e.move(Side.RIGHT);
            } else if (e.getMovingDirection().equals(Side.RIGHT)) {
                e.move(Side.LEFT);
            } else if (e.getMovingDirection().equals(Side.TOP)) {
                e.move(Side.BOTTOM);
            } else if (e.getMovingDirection().equals(Side.BOTTOM)) {
                e.move(Side.TOP);
            }
        }

    }

    @Override
    public void endContact(EntityBody other) {

    }

}