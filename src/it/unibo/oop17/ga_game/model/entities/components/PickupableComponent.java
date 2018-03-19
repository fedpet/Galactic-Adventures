package it.unibo.oop17.ga_game.model.entities.components;

/**
 * A pickupable component can be used to manage the addictions to the inventory.
 */
public interface PickupableComponent extends EntityComponent {

    void collect();

}
