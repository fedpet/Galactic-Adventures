package it.unibo.oop17.ga_game.model.physics;

import java.util.Iterator;
import java.util.stream.Stream;

import org.jbox2d.collision.AABB;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;
import org.jbox2d.dynamics.contacts.ContactEdge;

import it.unibo.oop17.ga_game.utils.Streams;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Box2D utility class.
 */
/* package-protected */ final class B2DUtils {
    /**
     * Box2D's extra thickness (in meters) applied to every body.
     */
    public static final float EXTRA_SKIN_THICKNESS = 0.1f;

    private B2DUtils() {
    }

    /**
     * Converts a {@link Vec2} to a {@link Point2D}.
     * 
     * @param vector
     *            The Vec2
     * @return The Point2D
     */
    public static Point2D vecToPoint(final Vec2 vector) {
        if (!vector.isValid()) {
            // weird Box2D behaviour fixes..
            return Point2D.ZERO;
        }
        return new Point2D(vector.x, vector.y);
    }

    /**
     * Converts a {@link Point2D} to a {@link Vec2}.
     * 
     * @param point
     *            The Point2D
     * @return The Vec2
     */
    public static Vec2 pointToVec(final Point2D point) {
        return new Vec2((float) point.getX(), (float) point.getY());
    }

    /**
     * Calculates a Box2D body's axis-aligned bounding box dimension.
     * 
     * @deprecated Box2D seems to give the wrong AABB if called before the first step
     * 
     * @param b2Body
     *            The Body
     * @return The Dimension2D
     */
    @Deprecated
    public static Dimension2D boundingBox(final Body b2Body) {
        return stream(b2Body.getFixtureList())
                .filter(f -> !f.isSensor())
                .map(f -> f.getAABB(0))
                .filter(f -> f.isValid())
                .map(a -> new AABB(a)) // copy to avoid side-effects
                .reduce((a1, a2) -> {
                    a1.combine(a2);
                    return a1;
                })
                .map(bb -> new Dimension2D(bb.upperBound.x - bb.lowerBound.x, bb.upperBound.y - bb.lowerBound.y))
                .orElseThrow(() -> new IllegalStateException("Body has no fixtures"));
    }

    /**
     * Utility method to check if a Body is involved in a Contact.
     * 
     * @param contact
     *            The Contact
     * @param body
     *            The Body
     * @return true if it's involved
     */
    public static boolean contains(final Contact contact, final Body body) {
        return contact.getFixtureA().getBody().equals(body) || contact.getFixtureB().getBody().equals(body);
    }

    /**
     * Utility method to check if a Body is involved in a ContactEdge.
     * 
     * @param contact
     *            The ContactEdge
     * @param body
     *            The Body
     * @return true if it's involved
     */
    public static boolean contains(final ContactEdge contact, final Body body) {
        return contains(contact.contact, body);
    }

    /**
     * Utility method to check if a Fixture is involved in a Contact.
     * 
     * @param contact
     *            The Contact
     * @param fixture
     *            The Fixture
     * @return true if it's involved
     */
    public static boolean contains(final Contact contact, final Fixture fixture) {
        return contact.getFixtureA().equals(fixture) || contact.getFixtureB().equals(fixture);
    }

    /**
     * Utility method to check if a Fixture is involved in a ContactEdge.
     * 
     * @param contact
     *            The ContactEdge
     * @param fixture
     *            The Fixture
     * @return true if it's involved
     */
    public static boolean contains(final ContactEdge contact, final Fixture fixture) {
        return contains(contact.contact, fixture);
    }

    /**
     * 
     * @param fixtureList
     *            The fixture
     * @return The corresponding Stream
     */
    public static Stream<Fixture> stream(final Fixture fixtureList) {
        return Streams.stream(new Iterator<Fixture>() {
            private Fixture list = fixtureList;
            @Override
            public boolean hasNext() {
                return list != null;
            }
            @Override
            public Fixture next() {
                final Fixture next = list;
                list = list.getNext();
                return next;
            }
        });
    }

    /**
     * 
     * @param contactEdgeList
     *            The ContactEdge
     * @return The corresponding Stream
     */
    public static Stream<ContactEdge> stream(final ContactEdge contactEdgeList) {
        return Streams.stream(new Iterator<ContactEdge>() {
            private ContactEdge list = contactEdgeList;

            @Override
            public boolean hasNext() {
                return list != null;
            }

            @Override
            public ContactEdge next() {
                final ContactEdge next = list;
                list = list.next;
                return next;
            }
        });
    }
}
