package it.unibo.oop17.ga_game.model.physics;

/**
 * Models collision sensors.
 */
public interface Sensor {
    /**
     * 
     * @return true if the sensor is in contact with external bodies.
     */
    boolean isTouching();

    /**
     * Sets a listener for this sensor collisions.
     * 
     * @param listener
     *            A Listener for this sensor collisions.
     */
    void setListener(CollisionListener listener);
}
