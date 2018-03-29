package it.unibo.oop17.ga_game.model.entities.components;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.events.BeginContactEvent;
import javafx.geometry.Point2D;

/**
 * A @Brain which attacks hated entities during contacts.
 * It just tries to use its weapon as if it was a melee one.
 */
public class ViolentBrain extends AbstractBrain {
    /**
     * @param personality
     *            Brain @Personality
     */
    public ViolentBrain(final Personality personality) {
        super(personality);
    }

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
                    weapon.use(weaponUseDirection(otherEntity.getBody()));
                });
            }
        });
    }

    private Point2D weaponUseDirection(final EntityBody other) {
        return getEntity().getBody().getPosition().subtract(other.getPosition());
    }
}
