package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.BasicEnemy;
import it.unibo.oop17.ga_game.view.BasicEnemyView;
import javafx.geometry.HorizontalDirection;

public class BasicEnemyController {
    private final BasicEnemy enemy;
    private final BasicEnemyView enemyView;

    public BasicEnemyController(final BasicEnemy enemy, final BasicEnemyView enemyView) {
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