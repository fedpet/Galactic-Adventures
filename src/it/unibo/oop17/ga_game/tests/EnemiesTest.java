package it.unibo.oop17.ga_game.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import it.unibo.oop17.ga_game.model.entities.BasicEnemy;
import it.unibo.oop17.ga_game.model.entities.FlyingEnemy;
import it.unibo.oop17.ga_game.model.physics.PhysicsEngine;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;

public class EnemiesTest {

    private final PhysicsEngine physics = PhysicsEngine.create(new Point2D(0, -30));

    @Test
    public void testBasicEnemy() {
        final BasicEnemy basicEnemy = new BasicEnemy(this.physics, new Point2D(4, -4));
        // assertFalse(basicEnemy.getMovement().canJump());
        assertTrue(basicEnemy.getMovement().getFaceDirection().equals(HorizontalDirection.RIGHT));
    }

    @Test
    public void testFlyingEnemy() throws InterruptedException {
        final PhysicsEngine physics = PhysicsEngine.create(new Point2D(0, -30));
        final FlyingEnemy flyingEnemy = new FlyingEnemy(physics, new Point2D(4, -4));
        assertTrue(flyingEnemy.getMovement().getFaceDirection().equals(HorizontalDirection.LEFT));
    }


}
