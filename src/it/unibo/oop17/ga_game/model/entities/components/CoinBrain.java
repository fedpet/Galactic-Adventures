package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.physics.BodyContact;

public class CoinBrain extends AbstractBrain {

    private final int value;

    public CoinBrain(int value) {
        this.value = value;
    }

    @Override
    public void beginContact(final BodyContact contact) {
        if (contact.getOtherBody().getOwner().isPresent()
                && contact.getOtherBody().getOwner().get().getInventory().isPresent()) {
            contact.getOtherBody().getOwner().get().getInventory().get().addMoney(value);
            this.post(new DestructionEvent(getEntity()));
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public Personality getPersonality() {
        return EntityPersonality.NONE;
    }

}
