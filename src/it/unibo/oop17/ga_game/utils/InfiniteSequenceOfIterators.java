package it.unibo.oop17.ga_game.utils;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Generates an infinite sequence starting with a given iterator and continuing with the ones supplied by a given
 * supplier.
 * 
 * @param <T>
 *            Elements type
 * @return The sequence.
 */
public final class InfiniteSequenceOfIterators<T> implements InfiniteSequence<T> {
    private final Supplier<Iterator<T>> supplier;
    private Iterator<T> it;

    /**
     * @param beginning
     *            Starting iterator for the sequence.
     * @param next
     *            A supplier for next iterators.
     */
    public InfiniteSequenceOfIterators(final Iterator<T> beginning, final Supplier<Iterator<T>> next) {
        it = Objects.requireNonNull(beginning);
        supplier = next;
    }

    @Override
    public T get() {
        if (!it.hasNext()) {
            it = supplier.get();
        }
        return it.next();
    }
}
