package it.unibo.oop17.ga_game.model.entities.components;

/**
 * Standard @Life.
 */
public class LinearLife extends AbstractLife {

    /**
     * 
     * @param max
     *            Maximum amount of life.
     * @param current
     *            Current amount of life.
     */
    public LinearLife(final int max, final int current) {
        super(max, current);
    }

    /**
     * 
     * @param max
     *            Maximum amount of life. Current amount will also be the maximum one.
     */
    public LinearLife(final int max) {
        this(max, max);
    }
}
