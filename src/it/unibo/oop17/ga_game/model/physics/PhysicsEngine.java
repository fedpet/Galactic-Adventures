package it.unibo.oop17.ga_game.model.physics;

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
     * @return A factory for bodies.
     */
    BodyFactory bodyFactory();

    /**
     * Static factory.
     * 
     * @param gravity
     *            The gravity for this world.
     * @return The PhysicsEngine
     */
    static PhysicsEngine create(final Point2D gravity) {
        return new B2DPhysicsEngine(gravity);
    }
}
