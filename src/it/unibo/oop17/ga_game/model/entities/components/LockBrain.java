package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.entities.events.DestructionEvent;
import it.unibo.oop17.ga_game.model.physics.BodyContact;

public class LockBrain extends AbstractBrain {

    private final KeyLockType type;

    public LockBrain(KeyLockType type) {
        this.type = type;
    }

    @Override
    public void beginContact(final BodyContact contact) {
        if (contact.getOtherBody().getOwner().isPresent()
                && contact.getOtherBody().getOwner().get().getInventory().isPresent()
                && contact.getOtherBody().getOwner().get().getInventory().get().getKeysBunch().contains(type)) {
            this.post(new DestructionEvent(getEntity()));
        }
    }

    public KeyLockType getType() {
        return type;
    }

    @Override
    public Personality getPersonality() {
        return EntityPersonality.NONE;
    }

}