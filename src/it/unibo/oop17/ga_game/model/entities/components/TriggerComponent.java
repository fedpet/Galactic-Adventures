package it.unibo.oop17.ga_game.model.entities.components;

/**
 * An @EntityComponent for trigger entities.
 */
public interface TriggerComponent extends EntityComponent {

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
