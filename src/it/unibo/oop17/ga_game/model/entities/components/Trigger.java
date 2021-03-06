package it.unibo.oop17.ga_game.model.entities.components;

/**
 * An {@link EntityComponent} object for trigger entities.
 */
public interface Trigger extends EntityComponent {

    /**
     * 
     * @return The triggered state.
     */
    boolean hasTriggered();

    /**
     * It triggers the component.
     * 
     */
    void trigger();

}
