package it.unibo.oop17.ga_game.model.physics;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.physics.box2d.B2DPhysicsEngine;
import javafx.geometry.Point2D;

/**
 * Models our PhysicsEngine.
 */
public interface PhysicsEngine {
    /**
     * Run the physics simulation for the given time delta. Note: it's better to use
     * a constant time delta.
     * 
     * @param dt
     *            Time delta in seconds. It's better for it to be constant.
     */
    void update(double dt);

    /**
     * 
     * @return A builder for bodies.
     */
    BodyBuilder bodyBuilder();

    /**
     * Removes the body from the simulation.
     * 
     * @param body
     *            the @EntityBody to remove.
     */
    void remove(EntityBody body);

    /**
     * Creates an instance of our best PhysicsEngine.
     * 
     * @param gravity
     *            The gravity for this world.
     * @return The PhysicsEngine
     */
    static PhysicsEngine create(final Point2D gravity) {
        return new B2DPhysicsEngine(gravity);
    }
}
