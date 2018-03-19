package it.unibo.oop17.ga_game.model.entities.components;

import java.util.function.Consumer;

public class PickupableComponentImpl extends AbstractEntityComponent implements PickupableComponent {

    Consumer<Inventory> inventoryAdder;
    
    public PickupableComponentImpl(final Consumer<Inventory> inventoryAdder) {
        this.inventoryAdder = inventoryAdder;
    }
    
    @Override
    public void collect() {
            getEntity().getBody().getContacts()
                    .filter(contact -> contact.getOtherBody().getOwner().isPresent())
                    .map(contact -> contact.getOtherBody().getOwner().get())
                    .filter(entity -> entity.get(Inventory.class).isPresent())
                    .map(entity -> entity.get(Inventory.class).get())
                    .forEach(x -> inventoryAdder.accept(x));
        }
    
}
