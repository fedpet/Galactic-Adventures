package it.unibo.oop17.ga_game.model.physics.box2d;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.physics.BodyFactory;
import it.unibo.oop17.ga_game.model.physics.CollisionListener;
import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.Point2D;

/**
 * Manages the game world physics through Box2D.
 */
public final class B2DPhysicsEngine implements PhysicsEngine {
    private static final int VELOCITY_ITERATIONS = 8; // recommended box2d values
    private static final int POSITION_ITERATIONS = 3;
    private final Map<Body, B2DEntityBody> collisionMap = new HashMap<>();
    // we keep track of bodies to remove because they can't be removed during a world.step call
    private final Set<B2DEntityBody> bodiesToRemove = new LinkedHashSet<>();
    private final World world;

    /**
     * 
     * @param gravity
     *            The force of the gravity
     */
    public B2DPhysicsEngine(final Point2D gravity) {
        world = new World(B2DUtils.pointToVec(gravity));
        world.setContactListener(new MyContactListener());
    }

    /**
     * Run the physics simulation for the given time delta. Note: it's better to use
     * a constant time delta.
     * 
     * @param dt
     *            Time delta in seconds. It's better for it to be constant.
     */
    @Override
    public void update(final double dt) {
        destroyRemovedBodies();
        if ((float) dt != 0) { // avoid stepping with 0 as DT to avoid weird bugs like positions and velocities being
                               // sets to NaN
            world.step((float) dt, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
        }
    }

    @Override
    public BodyFactory bodyFactory() {
        return new B2DBodyFactory((bodyDef, size) -> {
            final Body b2Body = world.createBody(bodyDef);
            final B2DBodyFacade body = new B2DBodyFacade(b2Body, size, collisionMap);
            collisionMap.put(b2Body, body);
            return body;
        });
    }

    @Override
    public void remove(final EntityBody body) {
        if (body instanceof B2DEntityBody) {
            bodiesToRemove.add((B2DEntityBody) body);
        }
    }

    private void destroyRemovedBodies() {
        bodiesToRemove.stream()
                .map(B2DEntityBody::getB2DBody)
                .peek(collisionMap::remove)
                .forEach(world::destroyBody);
        bodiesToRemove.clear();
    }

    private final class MyContactListener implements ContactListener {
        @Override
        public void beginContact(final Contact contact) {
            if (isThereARemovedBody(contact)) {
                handleContactBetweenRemovedBodies(contact);
            } else {
                callbacks(contact, (listener, other) -> listener.beginContact(other));
            }
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
            if (isThereARemovedBody(contact)) {
                handleContactBetweenRemovedBodies(contact);
            }
            // report the end of contact anyway
            callbacks(contact, (listener, other) -> listener.endContact(other));
        }

        private void callbacks(final Contact contact, final BiConsumer<CollisionListener, EntityBody> handler) {
            if (contact.isEnabled()) {
                final B2DEntityBody first = collisionMap.get(contact.getFixtureA().getBody());
                final B2DEntityBody second = collisionMap.get(contact.getFixtureB().getBody());

                if (first != null && second != null) {
                    handler.accept(first, second);
                    handler.accept(second, first);
                }
            }
        }

        private boolean isThereARemovedBody(final Contact contact) {
            final B2DEntityBody first = collisionMap.get(contact.getFixtureA().getBody());
            final B2DEntityBody second = collisionMap.get(contact.getFixtureB().getBody());
            return bodiesToRemove.contains(first) || bodiesToRemove.contains(second);
        }

        private void handleContactBetweenRemovedBodies(final Contact contact) {
            contact.setEnabled(false);
            // contact.flagForFiltering();
        }
    }
}
