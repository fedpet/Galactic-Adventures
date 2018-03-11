package it.unibo.oop17.ga_game.model.entities.components;

import javafx.geometry.Point2D;

public class FlyingEnemyBrain extends AbstractEntityComponent implements Brain {

    private Point2D startingPoint = null;
    private float angle = 0f;
    private static final float MAX_X = 5f;
    private static final float MAX_Y = 5f;

    @Override
    public void beginContact(final EntityBody other) {

    }


    @Override
    public void endContact(final EntityBody other) {

    }

    @Override
    public void update(final double dt) {
        if (this.startingPoint == null) {
            this.startingPoint = getEntity().getBody().getPosition();
        }
        final Point2D currentPoint = getEntity().getBody().getPosition();
        final double random = angle++ * 2.0 * Math.PI / 360.0; // this will convert it to rads
        final float x = (int) (Math.cos(random) * MAX_X) + (float) this.startingPoint.getX();
        final float y = (int) (Math.sin(random) * MAX_Y) + (float) this.startingPoint.getY();

        getEntity().getMovement().move(new Point2D(x, y).subtract(currentPoint));
    }

    

}