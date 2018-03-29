package it.unibo.oop17.ga_game.model.entities.components;

import java.util.function.Consumer;

public class PickupableBrain extends AbstractBrain {

    private final Consumer<Inventory> inventoryAdder;

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