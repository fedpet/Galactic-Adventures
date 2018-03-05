package it.unibo.oop17.ga_game.model.physics;

import it.unibo.oop17.ga_game.model.EntityBody;
import it.unibo.oop17.ga_game.model.GroundEntityBody;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;

/**
 * Factory for EntityBodies.
 */
public interface BodyFactory {
    /**
     * 
     * @param position
     *            Position (relative to the body's center)
     * @param size
     *            Width and height
     * @return {@link GroundEntityBody}
     */
    GroundEntityBody createGroundCreature(Point2D position, Dimension2D size);

    /**
     * A terrain body is not subject to forces.
     * 
     * @param position
     *            Position (relative to the body's center)
     * @param size
     *            Width and height
     * @return {@link EntityBody}
     */
    EntityBody createTerrain(Point2D position, Dimension2D size);
}
