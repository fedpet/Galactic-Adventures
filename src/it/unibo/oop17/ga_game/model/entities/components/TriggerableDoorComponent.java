package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.entities.events.FinishedLevelEvent;

/**
 * A @OneTimeTriggerable component for door entities; if triggered, it posts a finished level event at player contact.
 */
public class TriggerableDoorComponent extends OneTimeTriggerable {

    public TriggerableDoorComponent(final String password, final boolean triggered) {
        super(password, triggered);
    }

    @Override
    public void update(final double dt) {
        super.update(dt);
        getEntity().getBody()
                .getContacts()
                .map(EntityBody::getOwner)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(entity -> entity.get(Inventory.class).isPresent())
                .findAny()
                .ifPresent(entity -> {
                    post(new FinishedLevelEvent(entity));
            });
    }

}
