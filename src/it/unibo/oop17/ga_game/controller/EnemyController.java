package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Enemy;
import it.unibo.oop17.ga_game.view.EnemyView;
import javafx.geometry.HorizontalDirection;

public class EnemyController {
    private final Enemy enemy;
    private final EnemyView enemyView;

    public EnemyController(final Enemy enemy, final EnemyView enemyView) {
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