package it.unibo.oop17.ga_game.model.physics;

import java.util.function.Consumer;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * Manages the game world physics.
 */
public class PhysicsEngine {
    private static final int VELOCITY_ITERATIONS = 8; // recommended box2d values
    private static final int POSITION_ITERATIONS = 3;

    private final World world;

    /**
     * 
     * @param gravity
     *            The force of the gravity
     */
    public PhysicsEngine(final Vec2 gravity) {
        world = new World(gravity.clone());
        world.setContactListener(new MyContactListener());
    }

    /**
     * Run the physics simulation for the given time delta. Note: it's better to use
     * a constant time delta.
     * 
     * @param dt
     *            Time delta in seconds. It's better for it to be constant.
     */
    public void update(final double dt) {
        world.step((float) dt, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
    }

    public World getWorld() {
        return world;
    }

    private static final class MyContactListener implements ContactListener {
        @Override
        public void beginContact(final Contact contact) {
            callbacks(contact, c -> c.beginContact(contact));
        }

        @Override
        public void preSolve(final Contact contact, final Manifold manifold) {
            // not needed in our game. this is often used for advanced collision handling.
        }

        @Override
        public void postSolve(final Contact contact, final ContactImpulse contactImpulse) {
            // not needed in our game. this is often used for advanced collision handling.
        }

        @Override
        public void endContact(final Contact contact) {
            callbacks(contact, c -> c.endContact(contact));
        }

        private void callbacks(final Contact contact, final Consumer<CollisionListener> handler) {
            if (contact.isEnabled()) {
                // here we do a bad thing but it's the Box2D's official way to do that...
                final CollisionListener first = (CollisionListener) contact.getFixtureA().getUserData();
                final CollisionListener second = (CollisionListener) contact.getFixtureB().getUserData();
                if (first != null) {
                    handler.accept(first);
                }
                if (second != null) {
                    handler.accept(second);
                }
            }
        }
    }
}
