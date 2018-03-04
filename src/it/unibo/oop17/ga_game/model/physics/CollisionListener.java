package it.unibo.oop17.ga_game.model.physics;

import org.jbox2d.dynamics.contacts.Contact;

/**
 * Interface for collision detection.
 */
public interface CollisionListener {

    /**
     * Will be called by the physics engine at the start of a contact with an
     * external Body.
     * 
     * @param contact
     *            Contact details
     */
    void beginContact(Contact contact);

    /**
     * Will be called by the physics engine at the end of a contact with an external
     * ContactListener.
     * 
     * @param contact
     *            Contact details
     */
    void endContact(Contact contact);
}
