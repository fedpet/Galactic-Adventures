package it.unibo.oop17.ga_game.model;

/**
 * A basic @Timer.
 */
public final class TimerImpl implements Timer {
    private final double end;
    private double elapsed;

    /**
     * @param time
     *            The timer will be elapsed after the given time in seconds.
     */
    public TimerImpl(final double time) {
        end = time;
    }

    @Override
    public void restart() {
        elapsed = 0;
    }

    @Override
    public void update(final double dt) {
        if (dt < 0) {
            throw new IllegalArgumentException("Can't rewind a timer passing a negative dt");
        }
        elapsed += dt;
    }

    @Override
    public boolean isElapsed() {
        return elapsed >= end;
    }
}
