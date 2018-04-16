package it.unibo.oop17.ga_game.model.entities.components;

/**
 * An @EntityComponent for triggerable entities.
 */
public interface Triggerable extends EntityComponent {

    /**
     * Return the password associated to the component.
     * 
     * @return The password.
     */
    String getPassword();

    /**
     * It makes the component triggered.
     * 
     */
    void trigger();

    /**
     * 
     * @return The triggered state.
     */
    boolean isTriggered();

}
