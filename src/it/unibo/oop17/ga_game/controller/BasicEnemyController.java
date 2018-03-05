package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.BasicEnemy;
import javafx.geometry.HorizontalDirection;
import javafx.scene.Scene;

public class BasicEnemyController {
	private boolean movingLeft;
	private boolean movingRight;

	public BasicEnemyController(final Scene scene, final BasicEnemy enemy) {

		movingLeft = false;

		movingRight = true;

		enemy.move(HorizontalDirection.RIGHT);
	}

	public void updateMovingDirection(final BasicEnemy enemy) {

		if (movingLeft && !movingRight) {
			movingLeft = false;

			movingRight = true;
			enemy.move(HorizontalDirection.RIGHT);
		} else if (!movingLeft && movingRight) {
			movingLeft = true;

			movingRight = false;
			enemy.move(HorizontalDirection.LEFT);
		}

	}
}