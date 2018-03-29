package it.unibo.oop17.ga_game.model.physics.box2d;

import org.jbox2d.dynamics.Body;

import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.physics.CollisionListener;

/* package-protected */ interface B2DEntityBody extends EntityBody, CollisionListener {

    /**
     * 
     * @return The Box2D's body for internal use.
     */
    Body getB2DBody();
}
