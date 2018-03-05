package it.unibo.oop17.ga_game.model.physics;

import java.util.Optional;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.ContactEdge;

import javafx.geometry.Rectangle2D;

/**
 * Basic {@link Sensor} implementation.
 */
// TODO: sistema
/* package-private */ class SensorImpl implements Sensor {
    private final Fixture fixture;
    private Optional<CollisionListener> listener = Optional.empty();

    /**
     * 
     * @param owner
     *            The parent Body
     * @param rect
     *            The shape of this sensor
     */
    public SensorImpl(final Body owner, final Rectangle2D rect) {
        fixture = new B2DFixtureBuilder()
                .isSensor(true)
                .rectangular(rect)
                // .collisionListener(new MyCollisionListener())
                .buildOn(owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isTouching() {
        System.out.println(
                "isTouching");
        for (ContactEdge c = fixture.getBody().getContactList(); c != null; c = c.next) {
            System.out.println(
                    c.contact.isEnabled() + "," + c.contact.isTouching() + "," + Box2DUtils.contains(c, fixture));
            if (c.contact.isEnabled() && c.contact.isTouching() && Box2DUtils.contains(c, fixture)) {
                return true;
            }
        }
        System.out.println(
                "isTouching end--------");
        return false;
    }

    @Override
    public final void setListener(final CollisionListener listener) {
        this.listener = Optional.of(listener);
    }

    /*
     * private final class MyCollisionListener implements CollisionListener {
     * 
     * @Override
     * public void beginContact(final Contact contact) {
     * listener.ifPresent(l -> l.beginContact(contact));
     * }
     * 
     * @Override
     * public void endContact(final Contact contact) {
     * listener.ifPresent(l -> l.endContact(contact));
     * }
     * };
     */
}
