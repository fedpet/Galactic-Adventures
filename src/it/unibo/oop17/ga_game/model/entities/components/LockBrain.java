package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.KeyLockType;

/**
 * A @Brain that destroys the attached @Entity when an @Entity with a @Inventory
 * containing a Key of the same type collides with it.
 */
public class LockBrain extends AbstractBrain {

    private final KeyLockType type;

    /**
     * @param type
     *            Its @KeyLockType.
     */
    public LockBrain(final KeyLockType type) {
        super(EntityPersonality.NONE);
        this.type = type;
    }

    @Override
    protected final void handleContact(final EntityBody other) {
        other.getOwner().ifPresent(otherEntity -> {
            otherEntity.get(Inventory.class).ifPresent(inv -> {
                if (inv.getKeysBunch().contains(type)) {
                    getEntity().destroy();
                }
            });
        });
    }
}
