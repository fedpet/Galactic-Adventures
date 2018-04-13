package it.unibo.oop17.ga_game.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import it.unibo.oop17.ga_game.model.Timer;
import it.unibo.oop17.ga_game.model.TimerImpl;

/**
 * Test for @Timer.
 * It tests both @Timer.seconds and @TimerImpl.
 * Today they return an instance of the same class but it could change tomorrow.
 */
@RunWith(Parameterized.class)
public class TimerTest {
    private final Function<Double, Timer> timerFactory;

    /**
     * @param timerFactory
     *            A function to make the desired @Timer to test.
     */
    public TimerTest(final Function<Double, Timer> timerFactory) {
        this.timerFactory = timerFactory;
    }

    /**
     * JUnits way to test multiple implementations.
     * 
     * @return Arguments for the constructor.
     */
    @Parameters
    public static Collection<Function<Double, Timer>> data() {
        return Arrays.asList(TimerImpl::new, Timer::seconds);
    }

    /**
     * A timer should elapse after the given time.
     */
    @Test
    public void testElapsed() {
        final Timer timer = timerFactory.apply(2.0);
        assertFalse(timer.isElapsed());
        timer.update(1);
        assertFalse(timer.isElapsed());
        timer.update(1);
        assertTrue(timer.isElapsed());
    }

    /**
     * Can be restarted.
     */
    @Test
    public void testRestart() {
        final Timer timer = timerFactory.apply(1.0);
        timer.update(2);
        timer.restart();
        assertFalse(timer.isElapsed());
    }

    /**
     * Can't rewind a timer this way.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalTimeUpdate() {
        timerFactory.apply(1.0).update(-1);
    }
}
