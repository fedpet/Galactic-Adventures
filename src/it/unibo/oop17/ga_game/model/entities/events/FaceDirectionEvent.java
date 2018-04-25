package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import javafx.geometry.HorizontalDirection;

/**
 * Models a new face direction event.
 */
public class FaceDirectionEvent extends AbstractEntityEvent {
    private final HorizontalDirection face;

    /**
     * 
     * @param source
     *            The {@link Entity} source of this event.
     * @param face
     *            new face direction
     */
    public FaceDirectionEvent(final Entity source, final HorizontalDirection face) {
        super(source);
        this.face = face;
    }

    /**
     * 
     * @return The entity's new face direction.
     */
    public HorizontalDirection getDirection() {
        return face;
    }
}
