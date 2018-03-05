package it.unibo.oop17.ga_game.utils;

import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Utility class for streams.
 */
public final class Streams {

    private Streams() {

    }

    /**
     * Generates a {@link Stream} from an {@link Iterable}.
     * 
     * @param <T>
     *            Generic
     * @param it
     *            The {@link Iterable}
     * @return The {@link Stream}
     */
    public static <T> Stream<T> stream(final Iterable<T> it) {
        return StreamSupport.stream(it.spliterator(), false);
    }

    /**
     * Generates a {@link Stream} from an {@link Iterator}.
     * NOTE: unknown results may be produced if the supplied iterator is modified during the Stream traversal.
     * 
     * @param <T>
     *            Generic
     * @param it
     *            The {@link Iterator}
     * @return The {@link Stream}
     */
    public static <T> Stream<T> stream(final Iterator<T> it) {
        return stream(() -> it);
    }
}
