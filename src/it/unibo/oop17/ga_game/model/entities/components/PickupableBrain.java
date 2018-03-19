package it.unibo.oop17.ga_game.model.entities.components;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;

public class PickupableBrain extends AbstractBrain {

    @Subscribe
    public void beginContact(final BeginContactEvent contact) {
        contact.getOtherBody().getOwner().ifPresent(otherEntity -> {
            otherEntity.get(Inventory.class).ifPresent(inv -> {
                getEntity().get(PickupableComponent.class).ifPresent(pickup -> {
                    pickup.collect();
                });
                getEntity().destroy();
            });
        });
    }

    @Override
    public Personality getPersonality() {
        return EntityPersonality.NONE;
    }

}