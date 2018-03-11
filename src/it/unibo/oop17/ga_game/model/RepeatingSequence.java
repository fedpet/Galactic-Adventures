package it.unibo.oop17.ga_game.model;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Models a sequence which, at some point, repeats itself.
 * 
 * @param <T>
 *            Element type
 */
public interface RepeatingSequence<T> extends Supplier<T> {
    /**
     * Builds a sequence which goes back and forth.
     * 
     * @param list
     *            The list
     * @param <T>
     *            Element type
     * @return The sequence
     */
    static <T> RepeatingSequence<T> backAndForth(final List<T> list) {
        if (list.isEmpty()) {
            throw new IllegalArgumentException("List size must be > 0");
        }

        return new RepeatingSequence<T>() {
            private final List<T> myList = new LinkedList<>(list);
            private Iterator<T> it = myList.iterator();

            @Override
            public T get() {
                if (!it.hasNext()) {
                    Collections.reverse(myList);
                    it = myList.iterator();
                }
                return it.next();
            }
        };
    }
}
