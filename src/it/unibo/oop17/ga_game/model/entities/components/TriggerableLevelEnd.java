package it.unibo.oop17.ga_game.model.entities.components;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.entities.events.FinishedLevelEvent;

/**
 * A {@link OneTimeTriggerable} component for door entities;
 * if triggered, it posts a finished level event at player contact.
 */
public class TriggerableLevelEnd extends OneTimeTriggerable {

    /**
     * 
     * @param password
     *            A {@link Trigger} component can trigger this component if they have the same password.
     * @param triggered
     *            The initial state of the {@link Triggerable} component (if triggered or not).
     */
    public TriggerableLevelEnd(final String password, final boolean triggered) {
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
