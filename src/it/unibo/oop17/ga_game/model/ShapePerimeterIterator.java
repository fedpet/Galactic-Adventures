package it.unibo.oop17.ga_game.model;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.Point2D;

/**
 * An iterator for @Shape perimeter points.
 */
public final class ShapePerimeterIterator implements Iterator<Point2D> {
    private static final int PATHITERATOR_BUFFER = 6;
    private final PathIterator it;
    private Iterator<Point2D> pointsIt = Collections.<Point2D>emptyList().iterator();

    /**
     * 
     * @param shape
     *            Iterates this @Shape perimeter.
     * @param transform
     *            An @AffineTransform applied to the shape coordinates.
     */
    public ShapePerimeterIterator(final Shape shape, final AffineTransform transform) {
        it = shape.getPathIterator(transform);
        pointsIt = extractPointsFromCurrentSegment(it).iterator();
        it.next();
    }

    /**
     * 
     * @param shape
     *            Iterates this @Shape perimeter.
     */
    public ShapePerimeterIterator(final Shape shape) {
        this(shape, null);
    }

    @Override
    public boolean hasNext() {
        return !it.isDone() && extractPointsFromCurrentSegment(it).iterator().hasNext();
    }

    @Override
    public Point2D next() {
        if (hasNext() && !pointsIt.hasNext()) {
            pointsIt = extractPointsFromCurrentSegment(it).iterator();
            it.next();
        }
        return pointsIt.next();
    }

    private List<Point2D> extractPointsFromCurrentSegment(final PathIterator it) {
        final double[] buffer = new double[PATHITERATOR_BUFFER];
        final int type = it.currentSegment(buffer);
        List<Point2D> points;
        switch (type) {
        case PathIterator.SEG_MOVETO:
        case PathIterator.SEG_LINETO:
            points = extractConsecutivePoints(buffer, 1);
            break;
        case PathIterator.SEG_QUADTO:
            points = extractConsecutivePoints(buffer, 2);
            break;
        case PathIterator.SEG_CUBICTO:
            points = extractConsecutivePoints(buffer, 3);
            break;
        case PathIterator.SEG_CLOSE:
            points = Collections.emptyList();
            break;
        default:
            throw new IllegalArgumentException("Unknown PathIterator type " + type);
        }
        return points;
    }

    private List<Point2D> extractConsecutivePoints(final double[] buffer, final int count) {
        final List<Point2D> list = new ArrayList<>(count);
        for (int i = 0; i < count * 2; i += 2) {
            list.add(new Point2D(buffer[i], buffer[i + 1]));
        }
        return list;
    }
}
