package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.FlyingEnemy;
import it.unibo.oop17.ga_game.view.FlyingEnemyView;
import javafx.geometry.HorizontalDirection;

public class FlyingEnemyController {
    private final FlyingEnemy enemy;
    private final FlyingEnemyView enemyView;

    public FlyingEnemyController(final FlyingEnemy enemy, final FlyingEnemyView enemyView) {
        this.enemy = enemy;
        this.enemyView = enemyView;
    }

    public void updateView() {
        if (this.enemy.getMovement().getFaceDirection().equals(HorizontalDirection.LEFT)) {
            this.enemyView.setScaleX(Math.abs(this.enemyView.getScaleX()));
        } else {
            this.enemyView.setScaleX(-Math.abs(this.enemyView.getScaleX()));
        }
    }
}