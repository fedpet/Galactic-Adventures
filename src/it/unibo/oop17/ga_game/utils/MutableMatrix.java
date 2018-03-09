package it.unibo.oop17.ga_game.utils;

import java.util.List;
import java.util.stream.Stream;

/**
 * Models a mutable @Matrix.
 * 
 * @param <E>
 *            The type of cells.
 */
public interface MutableMatrix<E> extends Matrix<E> {
    /**
     * 
     * @param row
     *            The row number (zero-based)
     * @param column
     *            The column number (zero-based)
     * @param element
     *            The new element. Nulls are not supported.
     */
    void set(int row, int column, E element);

    /**
     * Static factory to create MutableMatrices.
     * 
     * @param <E>
     *            The type of cells.
     * @param rows
     *            A @Stream of columns where a column is a @Stream of cells.
     * @return A MutableMatrix
     */
    static <E> MutableMatrix<E> create(final Stream<Stream<E>> rows) {
        return new ListOfListsMatrix<>(rows);
    }

    /**
     * Static factory to create MutableMatrices.
     * 
     * @param <E>
     *            The type of cells.
     * @param rows
     *            A @List of columns where a column is a @List of cells.
     * @return A MutableMatrix
     */
    static <E> MutableMatrix<E> create(final List<List<E>> rows) {
        return create(rows.stream().map(column -> column.stream()));
    }
}
