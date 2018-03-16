package it.unibo.oop17.ga_game.model.entities.components;

/**
 * A dummy brain doing nothing.
 */
public final class EmptyBrain extends AbstractBrain {
    @Override
    public void update(final double dt) {
    }

    @Override
    public void beginContact(final EntityBody other) {
    }

    @Override
    public void endContact(final EntityBody other) {
    }

    @Override
    public Personality getPersonality() {
        return EntityPersonality.NONE;
    }
}
