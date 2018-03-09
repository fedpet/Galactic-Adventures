package it.unibo.oop17.ga_game.utils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Simple implementation of a generic @MutableMatrix.
 * 
 * @param <E>
 *            The type of the cells
 */
public final class ListOfListsMatrix<E> implements MutableMatrix<E> {
    private final List<List<E>> rows; // list of columns

    /**
     * 
     * @param rows
     *            A @Stream of columns. A columns is a @Stream of cells.
     *            Null cells are not supported
     */
    public ListOfListsMatrix(final Stream<Stream<E>> rows) {
        this.rows = rows.map(inner -> inner.collect(Collectors.toList())).collect(Collectors.toList());
        this.rows.stream().filter(column -> column.size() != getColumns()).findAny().ifPresent(col -> {
            throw new IllegalArgumentException("Columns must have the same size!");
        });
        this.rows.stream().forEach(column -> column.stream().filter(cell -> cell == null).findAny().ifPresent(c -> {
            throw new IllegalArgumentException("Null cells are not supported");
        }));
    }

    @Override
    public int getRows() {
        return rows.size();
    }

    @Override
    public int getColumns() {
        return getRows() > 0 ? rows.get(0).size() : 0;
    }

    @Override
    public E getCell(final int row, final int column) {
        return rows.get(row).get(column);
    }

    @Override
    public void set(final int row, final int column, final E element) {
        rows.get(row).set(column, Objects.requireNonNull(element));
    }
}
