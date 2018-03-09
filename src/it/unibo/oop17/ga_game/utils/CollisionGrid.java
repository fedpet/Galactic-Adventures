package it.unibo.oop17.ga_game.utils;

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
}
