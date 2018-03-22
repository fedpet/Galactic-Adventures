package it.unibo.oop17.ga_game.model.entities.components;

import java.util.function.Consumer;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;

public class PickupableBrain extends AbstractBrain {

    Consumer<Inventory> inventoryAdder;

    public PickupableBrain(final Consumer<Inventory> inventoryAdder) {
        super(EntityPersonality.NONE);
        this.inventoryAdder = inventoryAdder;
    }

    @Subscribe
    public void beginContact(final BeginContactEvent contact) {
        contact.getOtherBody().getOwner().ifPresent(otherEntity -> {
            otherEntity.get(Inventory.class).ifPresent(inv -> {
                inventoryAdder.accept(inv);
                getEntity().destroy();
            });
        });
    }
}