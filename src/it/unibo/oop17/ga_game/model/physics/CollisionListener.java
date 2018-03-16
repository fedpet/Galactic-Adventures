package it.unibo.oop17.ga_game.model.physics;

/**
 * Interface for collision detection.
 */
public interface CollisionListener {

    /**
     * Will be called by the physics engine at the start of a contact with an external @EntityBody.
     * 
     * @param contact
     *            the @BodyContact
     */
    void beginContact(BodyContact contact);

    /**
     * Will be called by the physics engine at the end of a contact with an external @EntityBody.
     * 
     * @param contact
     *            the @BodyContact
     */
    void endContact(BodyContact contact);
}
