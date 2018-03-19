package it.unibo.oop17.ga_game.model.entities.components;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;

public class CoinBrain extends AbstractBrain {

    private final int value;

    public CoinBrain(final int value) {
        this.value = value;
    }


    @Subscribe
    public void beginContact(final BeginContactEvent contact) {
        contact.getOtherBody().getOwner().ifPresent(entity -> {
            entity.get(Inventory.class).ifPresent(inv -> {
                inv.addMoney(value);
                getEntity().destroy();
            });
        });
    }

    public int getValue() {
        return value;
    }

    @Override
    public Personality getPersonality() {
        return EntityPersonality.NONE;
    }

}
