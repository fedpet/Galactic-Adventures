package it.unibo.oop17.ga_game.model.entities.components;

public interface TriggerableComponent extends EntityComponent {

    String getPassword();

    void trigger();

    boolean isTriggered();

}
