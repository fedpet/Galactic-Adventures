package it.unibo.oop17.ga_game.utils;

import java.util.stream.IntStream;

/**
 * Helper to translate solid tiles to static bodies.
 */
public interface CollisionGrid {
    /**
     * 
     * @return The number of rows
     */
    int getRows();

    /**
     * 
     * @return The number of columns
     */
    int getColumns();

    /**
     * 
     * @param row
     *            Row
     * @param column
     *            Column
     * @return true if the cell is solid
     */
    boolean isSolid(int row, int column);

    /**
     * Converts a @CollisionGrid to a @MutableMatrix of Booleans where true means a solid cell.
     * Changing the Matrix does not change the CollisionGrid
     * 
     * @param grid
     *            The grid
     * @return @MutableMatrix of Booleans
     */
    static MutableMatrix<Boolean> asMatrix(final CollisionGrid grid) {
        return MutableMatrix.create(IntStream.range(0, grid.getRows())
                .mapToObj(row -> IntStream.range(0, grid.getColumns()).mapToObj(column -> grid.isSolid(row, column))));
    }
}
