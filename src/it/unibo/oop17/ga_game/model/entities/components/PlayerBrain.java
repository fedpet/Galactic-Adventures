package it.unibo.oop17.ga_game.model.entities.components;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;

/**
 * Attacks EVILs.
 */
public final class PlayerBrain extends AbstractBrain {
    /**
     * When a contact happens we check if we're touching an hated entity. In this case we attack it!
     * 
     * @param contact
     *            The @BeginContactEvent
     */
    @Subscribe
    public void beginContact(final BeginContactEvent contact) {
        contact.getOtherBody().getOwner().ifPresent(otherEntity -> {
            if (hate(otherEntity)) {
                getEntity().get(Weapon.class).ifPresent(weapon -> {
                    weapon.use(contact.getPoint());
                });
            }
        }); 
    }

    @Override
    public Personality getPersonality() {
        return EntityPersonality.GOOD;
    }
}
