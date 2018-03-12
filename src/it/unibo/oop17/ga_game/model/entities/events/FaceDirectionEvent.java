package it.unibo.oop17.ga_game.model.entities.events;

import javafx.geometry.HorizontalDirection;

/**
 * Models a new face direction event.
 */
public class FaceDirectionEvent implements EntityEvent {
    private final HorizontalDirection face;

    /**
     * 
     * @param face
     *            new face direction
     */
    public FaceDirectionEvent(final HorizontalDirection face) {
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
