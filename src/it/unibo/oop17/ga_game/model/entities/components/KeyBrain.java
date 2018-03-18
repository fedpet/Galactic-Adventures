package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.physics.BodyContact;

public class KeyBrain extends AbstractBrain {

    private final KeyLockType type;

    public KeyBrain(final KeyLockType type) {
        this.type = type;
    }

    @Override
    public void beginContact(final BodyContact contact) {
        contact.getOtherBody().getOwner().ifPresent(otherEntity -> {
            otherEntity.getInventory().ifPresent(inv -> {
                inv.add(type);
                getEntity().destroy();
            });
        });
    }

    public KeyLockType getType() {
        return type;
    }

    @Override
    public Personality getPersonality() {
        return EntityPersonality.NONE;
    }

}
