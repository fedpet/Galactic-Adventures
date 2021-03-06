package it.unibo.oop17.ga_game.model.physics.box2d;

import org.jbox2d.dynamics.BodyDef;

import javafx.geometry.Dimension2D;

/**
 * Provides a method to convert Box2D's BodyDef to {@link B2DBodyFacade} instance.
 */
/* package-protected */ interface B2DBodySpawner {
    B2DEntityBody spawn(BodyDef bodyDef, Dimension2D size);
}
