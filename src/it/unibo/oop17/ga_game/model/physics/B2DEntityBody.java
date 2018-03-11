package it.unibo.oop17.ga_game.model.physics;

import java.util.Optional;

import org.jbox2d.dynamics.Body;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;

/* package-protected */ interface B2DEntityBody extends EntityBody {

    /**
     * 
     * @return The Box2D's body for internal use.
     */
    Body getB2DBody();

    /**
     * The {@link CollisionListener} will be called by the physics engine during collisions.
     * 
     * @return The {@link CollisionListener}
     */
    Optional<CollisionListener> getCollisionListener();
}
