package it.unibo.oop17.ga_game.model.entities.components;

import java.util.function.Consumer;

/**
 * At contact with an inventory owner body, a PickupableBrain updates the inventory and makes the entity destroy.
 */
public class PickupableBrain extends AbstractBrain {

    private final Consumer<Inventory> inventoryAdder;

    /**
     * 
     * @param inventoryAdder
     *            The inventory update that occurs at contact with a inventory owner body.
     */
    public PickupableBrain(final Consumer<Inventory> inventoryAdder) {
        super(EntityPersonality.NONE);
        this.inventoryAdder = inventoryAdder;
    }

    @Override
    protected void handleContact(final EntityBody other) {
        other.getOwner().ifPresent(otherEntity -> {
            otherEntity.get(Inventory.class).ifPresent(inv -> {
                inventoryAdder.accept(inv);
                getEntity().destroy();
            });
        });
    }
}