package it.unibo.oop17.ga_game.model.entities.components;

/**
 * Base class for contact aware components.
 */
public abstract class AbstractContactAwareComponent extends AbstractEntityComponent {
    /**
     * Detects contacts with other bodies.
     */
    @Override
    public void update(final double dt) {
        super.update(dt);
        getEntity().getBody().getContacts().forEach(this::handleContact);
    }

    /**
     * Handle contact with other {@link EntityBody} object.
     * 
     * @param other
     *            Other {@link EntityBody} object in contact with ours.
     */
    protected abstract void handleContact(EntityBody other);
}
