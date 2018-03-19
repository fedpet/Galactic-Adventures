package it.unibo.oop17.ga_game.model.entities.components;

/**
 * Models the {@link Entity} brain which controls it and decides what to do in case of collisions.
 */
public interface Brain extends EntityComponent {

    /**
     * Brains have personality.
     * 
     * @return the @Personality.
     */
    Personality getPersonality();
}
