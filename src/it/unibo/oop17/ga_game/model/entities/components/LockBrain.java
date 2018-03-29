package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.KeyLockType;

public class LockBrain extends AbstractBrain {

    private final KeyLockType type;

    public LockBrain(final KeyLockType type) {
        super(EntityPersonality.NONE);
        this.type = type;
    }

    @Override
    protected void handleContact(final EntityBody other) {
        other.getOwner().ifPresent(otherEntity -> {
            otherEntity.get(Inventory.class).ifPresent(inv -> {
                if (inv.getKeysBunch().contains(type)) {
                    getEntity().destroy();
                }
            });
        });
    }
}