package it.unibo.oop17.ga_game.model;

/**
 * Models a generic Entity in our game.
 */
public interface Entity {
    /**
     * @return the Entity's Body
     */
    EntityBody getBody();

    /**
     * Used to synchronize the entities.
     * 
     * @param dt
     *            The time delta since the last call
     */
    void update(double dt);
}
