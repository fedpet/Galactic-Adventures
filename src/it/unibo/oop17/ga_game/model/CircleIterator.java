package it.unibo.oop17.ga_game.model;

import java.util.Iterator;

import javafx.geometry.Point2D;

public class CircleIterator implements Iterator<Point2D> {

    private final Point2D startingPoint;
    private double angle;
    private final double maxX;
    private final double maxY;

    public CircleIterator(final Point2D startingPoint, final float maxX, final float maxY) {
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
        return new Point2D(Math.cos(Math.toRadians(angle)) * maxX + this.startingPoint.getX(),
                Math.sin(Math.toRadians(angle++)) * maxY + this.startingPoint.getY());
    }

}
