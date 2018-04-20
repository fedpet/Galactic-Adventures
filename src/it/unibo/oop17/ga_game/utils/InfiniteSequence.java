package it.unibo.oop17.ga_game.utils;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Models an infinite sequence of elements.
 * 
 * @param <T>
 *            Element type
 */
public interface InfiniteSequence<T> extends Supplier<T> {
    /**
     * Generates an infinite sequence going back and forth. (circular).
     * 
     * @param list
     *            The list
     * @param <T>
     *            Element type
     * @return The sequence
     */
    static <T> InfiniteSequence<T> backAndForth(final List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List size must be > 0");
        }

        final List<T> myList = new LinkedList<>(list);
        return fromIterators(myList.iterator(), () -> {
            Collections.reverse(myList);
            final Iterator<T> it = myList.iterator();
            it.next(); // avoid duplicates
            return it;
        });
    }

    /**
     * Generates an infinite sequence restarting from the beginning as soon as the end is reached.
     * 
     * @param iterable
     *            the @Iterable from which to generate the sequence
     * @param <T>
     *            Iterable of T
     * @return A sequence repeating the iterable.
     */
    static <T> InfiniteSequence<T> repeat(final Iterable<T> iterable) {
        return fromIterators(iterable.iterator(), () -> iterable.iterator());
    }

    /**
     * Generates an infinite sequence starting with the first iterator and continuing with the ones supplied by the
     * supplier.
     * 
     * @param beginning
     *            starting iterator
     * @param <T>
     *            Iterable of T
     * @param iteratorSupplier
     *            A supplier for next iterators
     * @return The sequence.
     */
    static <T> InfiniteSequence<T> fromIterators(final Iterator<T> beginning, final Supplier<Iterator<T>> iteratorSupplier) {
        return new InfiniteSequenceOfIterators<>(beginning, iteratorSupplier);
    }
}
