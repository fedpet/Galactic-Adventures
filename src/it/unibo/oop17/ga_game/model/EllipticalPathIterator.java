package it.unibo.oop17.ga_game.model;

import java.util.Iterator;

import javafx.geometry.Point2D;

/**
 * Models an @Iterator object that provides an elliptical path.
 */
public class EllipticalPathIterator implements Iterator<Point2D> {

    private final Point2D startingPoint;
    private double angle;
    private final double maxX;
    private final double maxY;

    /**
     * @param startingPoint
     *            The starting point of the path.
     * @param maxX
     *            The horizontal axis of the path shape.
     * @param maxY
     *            The vertical axis of the path shape.
     */
    public EllipticalPathIterator(final Point2D startingPoint, final float maxX, final float maxY) {
        this.startingPoint = startingPoint;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    @Override
    public final boolean hasNext() {
        return true;
    }

    @Override
    public final Point2D next() {
        return new Point2D(Math.cos(Math.toRadians(angle)) * maxX + this.startingPoint.getX(),
                Math.sin(Math.toRadians(angle++)) * maxY + this.startingPoint.getY());
    }

}
