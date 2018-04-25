package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.KeyType;

/**
 * Models a {@link Brain} object that destroys the attached entity when an entity with a inventory
 * containing a Key of the same type collides with it.
 */
public class LockBrain extends AbstractBrain {

    private final KeyType type;

    /**
     * @param type
     *            The related key type.
     */
    public LockBrain(final KeyType type) {
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
