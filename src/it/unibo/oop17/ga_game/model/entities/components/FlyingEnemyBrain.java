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
        final float x = (float) (Math.cos(Math.toRadians(angle)) * MAX_X + this.startingPoint.getX());
        final float y = (float) (Math.sin(Math.toRadians(angle)) * MAX_Y + this.startingPoint.getY());
        angle++;

        getEntity().getMovement().move(new Point2D(x, y).subtract(getEntity().getBody().getPosition()));
    }

}