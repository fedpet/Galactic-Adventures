package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.entities.events.FinishedLevelEvent;

/**
 * A @OneTimeTriggerable component for door entities; if triggered, it posts a finished level event at player contact.
 */
public class TriggerableDoorComponent extends OneTimeTriggerable {

    /**
     * 
     * @param password
     *            A @TriggerComponent can trigger this component if they have the same password.
     * @param triggered
     *            The initial state of the @TriggerableComponent (if triggered or not).
     */
    public TriggerableDoorComponent(final String password, final boolean triggered) {
        super(password, triggered);
    }

    @Override
    public final void update(final double dt) {
        super.update(dt);
        getEntity().getBody()
                .getContacts()
                .map(EntityBody::getOwner)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter(entity -> entity.get(Inventory.class).isPresent())
                .findAny()
                .ifPresent(entity -> {
                    if (isTriggered()) {
                        post(new FinishedLevelEvent(entity));
                        detach();
                    }
            });
    }

}
