package it.unibo.oop17.ga_game.model.physics;

import org.jbox2d.dynamics.BodyDef;

import javafx.geometry.Dimension2D;

/**
 * Provides a method to convert Box2D's Body to @B2DBodyFacade.
 */
/* package-protected */ interface B2DBodySpawner {
    B2DBodyFacade spawn(BodyDef bodyDef, Dimension2D size);
}
