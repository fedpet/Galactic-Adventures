package it.unibo.oop17.ga_game.model.entities.components;

/**
 * An @EntityComponent for trigger entities.
 */
public interface TriggerComponent extends EntityComponent {

    /**
     * Return the password associated to the component.
     * 
     * @return The password.
     */
    String getPassword();

    /**
     * 
     * @return The triggered state.
     */
    boolean hasTriggered();

}
