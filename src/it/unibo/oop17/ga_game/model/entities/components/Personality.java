package it.unibo.oop17.ga_game.model.entities.components;

/**
 * Models personality.
 */
public interface Personality {
    /**
     * 
     * @param personality
     *            A personality
     * @return true if we hate the supplied personality
     */
    boolean hates(Personality personality);
}
