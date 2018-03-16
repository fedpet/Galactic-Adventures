package it.unibo.oop17.ga_game.model.entities.components;

/**
 * A dummy brain doing nothing.
 */
public final class EmptyBrain extends AbstractBrain {
    @Override
    public Personality getPersonality() {
        return EntityPersonality.NONE;
    }
}
