package it.unibo.oop17.ga_game.model;

import java.util.Iterator;

import javafx.geometry.Point2D;

public class CircleIterator implements Iterator<Point2D> {

    private final Point2D startingPoint;
    private float angle = 0f;
    private final float maxX;
    private final float maxY;

    public CircleIterator(Point2D startingPoint, float maxX, float maxY) {
        this.startingPoint = startingPoint;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Point2D next() {
        return new Point2D((float) (Math.cos(Math.toRadians(angle)) * maxX + this.startingPoint.getX()),
                (float) (Math.sin(Math.toRadians(angle++)) * maxY + this.startingPoint.getY()));
    }

}
