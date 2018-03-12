package it.unibo.oop17.ga_game.model.physics;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

import it.unibo.oop17.ga_game.utils.CollisionGrid;
import it.unibo.oop17.ga_game.utils.Matrix;
import it.unibo.oop17.ga_game.utils.MutableMatrix;
import javafx.geometry.Dimension2D;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

/**
 * Extracts Rectangles from a @CollisionGrid.
 */
public final class RectanglesExtractor {
    private int firstRow;
    private int firstColumn;
    private int bodyLength;
    private boolean buildingBody;

    /**
     * Extract @Rectangle2D from @CollisionGrid solid adiacent cells.
     * For Box2D we try to group cells horizontally first, then vertically.
     * This soften the problem of bodies getting stuck in between two cells.
     * 
     * @param topLeft
     *            The top-left coordinate to offset the rectangles.
     * @param grid
     *            The grid
     * @param tileSize
     *            The size of a single cell
     * @return A set of extracted rectangles
     */
    public Set<Rectangle2D> rectangles(final Point2D topLeft, final CollisionGrid grid,
            final Dimension2D tileSize) {
        buildingBody = false;
        final Set<Rectangle2D> rectangles = new HashSet<>();
        final MutableMatrix<Boolean> matrix = asMatrix(grid);

        rectangles.addAll(extractLongestRectangles(matrix, tileSize, topLeft, Orientation.HORIZONTAL, 2));
        rectangles.addAll(extractLongestRectangles(matrix, tileSize, topLeft, Orientation.VERTICAL, 0));
        return rectangles;
    }

    /**
     * Converts a @CollisionGrid to a @MutableMatrix of Booleans.
     * Changing the Matrix does not change the CollisionGrid
     * 
     * @param grid
     *            The grid
     * @return @MutableMatrix of Booleans
     */
    public static MutableMatrix<Boolean> asMatrix(final CollisionGrid grid) {
        return MutableMatrix.create(IntStream.range(0, grid.getRows())
                .mapToObj(row -> IntStream.range(0, grid.getColumns()).mapToObj(column -> grid.isSolid(row, column))));
    }

    /**
     * Extract the longest possible rectangles in the given orientation.
     * It modifies the input matrix removing the extracted rectangles.
     * 
     * @param matrix
     *            Input matrix
     * @param cellDimension
     *            size of a single cell
     * @param orientation
     *            orientation of the extraction
     * @param minLength
     *            minimum number of adiacent cells to make a @Rectangle2D
     * @return a Set of extracted rectangles
     */
    private Set<Rectangle2D> extractLongestRectangles(final MutableMatrix<Boolean> matrix,
            final Dimension2D cellDimension, final Point2D offset,
            final Orientation orientation, final int minLength) {

        final Set<Rectangle2D> rectangles = new HashSet<>();

        iterate(matrix, orientation,
                (row, column) -> checkCell(rectangles, row, column, matrix, cellDimension, offset, orientation,
                        minLength),
                () -> buildRect(rectangles, matrix, cellDimension, offset, orientation, minLength));

        return rectangles;
    }

    private void iterate(final Matrix<?> matrix, final Orientation orientation,
            final BiConsumer<Integer, Integer> inner, final Runnable postLoop) {
        final int maxX = orientation == Orientation.HORIZONTAL ? matrix.getRows() : matrix.getColumns();
        final int maxY = orientation == Orientation.HORIZONTAL ? matrix.getColumns() : matrix.getRows();
        for (int x = 0; x < maxX; x++) {
            for (int y = 0; y < maxY; y++) {
                if (orientation == Orientation.HORIZONTAL) {
                    inner.accept(x, y);
                } else {
                    inner.accept(y, x);
                }
            }
            postLoop.run();
        }
        postLoop.run();
    }

    private void checkCell(final Set<Rectangle2D> rectangles, final int row, final int column,
            final MutableMatrix<Boolean> matrix,
            final Dimension2D cellDimension, final Point2D offset,
            final Orientation orientation, final int minLength) {
        if (matrix.getCell(row, column)) {
            // this cell is solid
            if (buildingBody) {
                // if we're building a body we just extend its length
                bodyLength++;
            } else {
                // else we start building one starting from this cell
                buildingBody = true;
                firstRow = row;
                firstColumn = column;
                bodyLength = 1;
            }
        } else {
            // this cell is not solid
            buildRect(rectangles, matrix, cellDimension, offset, orientation, minLength);
        }
    }

    private void buildRect(final Set<Rectangle2D> rectangles,
            final MutableMatrix<Boolean> matrix,
            final Dimension2D cellDimension, final Point2D offset,
            final Orientation orientation, final int minLength) {
        if (buildingBody) {
            // if we were building a body we stop here
            buildingBody = false;
            if (bodyLength >= minLength) {
                markExtractedCells(matrix, firstRow, firstColumn, bodyLength, orientation);
                rectangles.add(
                        makeRect(firstRow, firstColumn, bodyLength, cellDimension, offset, orientation));
            }
        }
    }

    private static void markExtractedCells(final MutableMatrix<Boolean> matrix, final int firstRow,
            final int firstColumn, final int length,
            final Orientation orientation) {
        final int stepRow = orientation == Orientation.VERTICAL ? 1 : matrix.getRows();
        final int stepColumn = orientation == Orientation.HORIZONTAL ? 1 : matrix.getColumns();

        final int maxRows = orientation == Orientation.VERTICAL ? Math.min(matrix.getRows(), firstRow + length)
                : matrix.getRows();
        final int maxColumns = orientation == Orientation.HORIZONTAL
                ? Math.min(matrix.getColumns(), firstColumn + length)
                : matrix.getColumns();

        for (int row = firstRow; row < maxRows; row += stepRow) {
            for (int column = firstColumn; column < maxColumns; column += stepColumn) {
                matrix.set(row, column, false);
            }
        }
    }

    private static Rectangle2D makeRect(final int row, final int column, final int length, final Dimension2D cellSize,
            final Point2D offset,
            final Orientation orientation) {
        final double x = column * cellSize.getWidth() + offset.getX();
        final double y = row * cellSize.getHeight() + offset.getY();
        final double width = cellSize.getWidth() * (orientation == Orientation.HORIZONTAL ? length : 1);
        final double height = cellSize.getHeight() * (orientation == Orientation.VERTICAL ? length : 1);
        return new Rectangle2D(x, y, width, height);
    }
}
