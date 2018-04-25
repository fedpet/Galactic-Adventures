package it.unibo.oop17.ga_game.model;

/**
 * A timer keeps track of time.
 */
public interface Timer {
    /**
     * Restarts the timer.
     */
    void restart();

    /**
     * Acknowledge the passage of time.
     * 
     * @param dt
     *            Amount of time passed in seconds
     */
    void update(double dt);

    /**
     * @return true if the timer is elapsed.
     */
    boolean isElapsed();

    /**
     * Static factory for a timer working with seconds.
     * 
     * @param time
     *            in seconds
     * @return A {@link Timer} object.
     */
    static Timer seconds(final double time) {
        return new TimerImpl(time);
    }
}
