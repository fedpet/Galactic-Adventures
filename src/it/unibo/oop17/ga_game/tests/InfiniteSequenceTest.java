package it.unibo.oop17.ga_game.tests;

import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;

import it.unibo.oop17.ga_game.utils.InfiniteSequence;

/**
 * Tests {@link InfinitySequence} objects static factories.
 */
public class InfiniteSequenceTest {
    private static final int NUM_ITERATIONS = 10;

    /**
     * Tests {@link InfiniteSequence#backAndForth}.
     * It must not have duplicates.
     */
    @Test
    public void testBackAndForth() {
        testMatch(
                InfiniteSequence.backAndForth(Arrays.asList(1, 2, 3, 4)),
                Arrays.asList(1, 2, 3, 4, 3, 2));
    }

    /**
     * Tests {@link InfiniteSequence#repeat}.
     */
    @Test
    public void testRepeat() {
        testMatch(
                InfiniteSequence.repeat(Arrays.asList(1, 2, 3, 4)),
                Arrays.asList(1, 2, 3, 4, 1, 2, 3, 4));
    }

    private static <T> void testMatch(final InfiniteSequence<T> seq, final List<T> expected) {
        IntStream.range(0, NUM_ITERATIONS).forEach(n -> {
            expected.stream().filter(i -> !i.equals(seq.get())).findAny().ifPresent(i -> {
                fail("The supplied sequence doesn't match the expected one");
            });
        });
    }
}
