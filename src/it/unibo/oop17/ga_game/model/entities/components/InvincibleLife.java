package it.unibo.oop17.ga_game.model.entities.components;

/**
 * An invincible @Life cannot be altered.
 */
public class InvincibleLife extends AbstractLife {
    private static final int DEFAULT_AMOUNT = 1;

    /**
     * An invincible @Life cannot be altered.
     */
    public InvincibleLife() {
        super(DEFAULT_AMOUNT, DEFAULT_AMOUNT);
    }

    @Override
    protected final void addLife(final int amount) {
        // nothing happens to an invincible life.
    }
}
