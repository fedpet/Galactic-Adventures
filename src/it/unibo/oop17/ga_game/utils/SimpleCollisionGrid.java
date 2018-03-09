package it.unibo.oop17.ga_game.utils;

import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * Basic implementation of a @CollisionGrid.
 */
public class SimpleCollisionGrid implements CollisionGrid {
    private final int rows, columns;
    private final BiPredicate<Integer, Integer> tester;

    /**
     * 
     * @param rows
     *            Number of rows
     * @param columns
     *            Number of columns
     * @param tester
     *            Strategy to decide whether a cell is solid or not.
     *            Its first argument will be the row number while the second'll be the column.
     *            row and column numbers are in range [0, getRows()-1] and [0, getColumns()-1]
     *            It's guaranteed it won't receive out of bounds row and column numbers.
     */
    public SimpleCollisionGrid(final int rows, final int columns, final BiPredicate<Integer, Integer> tester) {
        if (rows < 0 || columns < 0 || columns == 0 && rows > 0 || rows == 0 && columns > 0) {
            throw new IllegalArgumentException("Invalid rows / columns number: " + rows + "x" + columns);
        }
        this.rows = rows;
        this.columns = columns;
        this.tester = Objects.requireNonNull(tester);
    }

    /**
     * Construct a @CollisionGrid from a @Matrix of Boolean.
     * 
     * @param matrix
     *            The @Matrix
     */
    public SimpleCollisionGrid(final Matrix<Boolean> matrix) {
        this(matrix.getRows(), matrix.getColumns(), (row, column) -> matrix.getCell(row, column));
    }

    @Override
    public final int getRows() {
        return rows;
    }

    @Override
    public final int getColumns() {
        return columns;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSolid(final int row, final int column) {
        checkBounds(row, column);
        return tester.test(row, column);
    }

    /**
     * 
     * @param row
     *            The row number
     * @param column
     *            The column number
     * @throws IndexOutOfBoundsException
     *             if row and column number are out of this grid's bounds
     */
    protected final void checkBounds(final int row, final int column) {
        if (row < 0 || row >= getRows() || column < 0 || column >= getColumns()) {
            throw new IndexOutOfBoundsException();
        }
    }
}
