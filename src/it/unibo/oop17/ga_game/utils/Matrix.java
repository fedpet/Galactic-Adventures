package it.unibo.oop17.ga_game.utils;

/**
 * Models a generic matrix.
 * 
 * @param <E>
 *            The type of cells.
 */
public interface Matrix<E> {
    /**
     * 
     * @return number of rows
     */
    int getRows();

    /**
     * 
     * @return number of columns
     */
    int getColumns();

    /**
     * 
     * @param row
     *            The row number (zero-based)
     * @param column
     *            The column number (zero-based)
     * @return the cell
     */
    E getCell(int row, int column);
}
