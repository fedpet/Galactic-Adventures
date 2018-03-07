package it.unibo.oop17.ga_game.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import it.unibo.oop17.ga_game.model.physics.RectanglesExtractor;
import it.unibo.oop17.ga_game.utils.CollisionGrid;
import it.unibo.oop17.ga_game.utils.Matrix;
import it.unibo.oop17.ga_game.utils.MutableMatrix;
import it.unibo.oop17.ga_game.utils.SimpleCollisionGrid;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Testing @RectanglesExtractor.
 */
public final class RectangleExtractorTest {

    /**
     * Should output 3 horizontal rectangles.
     */
    @Test
    public void testCube() {
        final Matrix<Boolean> matrix = MutableMatrix.create(
                Arrays.asList(
                        Arrays.asList(false, false, false),
                        Arrays.asList(true, true, false),
                        Arrays.asList(true, true, false),
                        Arrays.asList(true, true, false)));
        final CollisionGrid grid = new SimpleCollisionGrid(matrix);
        final Set<Rectangle2D> rects = new RectanglesExtractor().rectangles(Point2D.ZERO, grid,
                new Dimension2D(1, 1));

        assertEquals(rects, new HashSet<>(
                Arrays.asList(
                        new Rectangle2D(0, 1, 2, 1),
                        new Rectangle2D(0, 2, 2, 1),
                        new Rectangle2D(0, 3, 2, 1))));
    }

    /**
     * Contours.
     * Should output 4 rectangles:
     * the roof, the floor and 2 "columns"
     */
    @Test
    public void testContours() {
        final Matrix<Boolean> matrix = MutableMatrix.create(
                Arrays.asList(
                        Arrays.asList(true, true, true),
                        Arrays.asList(true, false, true),
                        Arrays.asList(true, false, true),
                        Arrays.asList(true, false, true),
                        Arrays.asList(true, true, true)));
        final CollisionGrid grid = new SimpleCollisionGrid(matrix);
        final Set<Rectangle2D> rects = new RectanglesExtractor().rectangles(Point2D.ZERO, grid,
                new Dimension2D(1, 1));

        assertEquals(rects, new HashSet<>(
                Arrays.asList(
                        new Rectangle2D(0, 0, 3, 1),
                        new Rectangle2D(0, 4, 3, 1),
                        new Rectangle2D(0, 1, 1, 3),
                        new Rectangle2D(2, 1, 1, 3))));
    }

    /**
     * Output should be shifted.
     */
    @Test
    public void testOffset() {
        final Matrix<Boolean> matrix = MutableMatrix.create(
                Arrays.asList(
                        Arrays.asList(false, false, false, false, false, false, false),
                        Arrays.asList(false, true, true, false, false, false, false),
                        Arrays.asList(false, false, false, false, true, true, false),
                        Arrays.asList(false, false, false, false, false, false, false),
                        Arrays.asList(false, false, false, false, false, false, false)));
        final CollisionGrid grid = new SimpleCollisionGrid(matrix);
        final Set<Rectangle2D> rects = new RectanglesExtractor().rectangles(new Point2D(1, 1), grid,
                new Dimension2D(1, 1));

        assertEquals(rects,
                new HashSet<>(Arrays.asList(new Rectangle2D(1 + 1, 1 + 1, 2, 1), new Rectangle2D(4 + 1, 2 + 1, 2, 1))));
    }

    /**
     * Double the dimension.
     */
    @Test
    public void testDimension() {
        final Matrix<Boolean> matrix = MutableMatrix.create(
                Arrays.asList(
                        Arrays.asList(false, true, false),
                        Arrays.asList(false, true, false),
                        Arrays.asList(false, false, false)));
        final CollisionGrid grid = new SimpleCollisionGrid(matrix);
        final Set<Rectangle2D> rects = new RectanglesExtractor().rectangles(Point2D.ZERO, grid,
                new Dimension2D(2, 2));

        assertEquals(rects,
                new HashSet<>(Arrays.asList(new Rectangle2D(2, 0, 2, 4))));
    }
}
