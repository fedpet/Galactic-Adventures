package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Enemy;
import it.unibo.oop17.ga_game.view.EnemyView;
import it.unibo.oop17.ga_game.view.ViewUtils;

public class EnemyController {
    private final Enemy enemy;
    private final EnemyView enemyView;

    public EnemyController(final Enemy enemy, final EnemyView enemyView) {
        this.enemy = enemy;
        this.enemyView = enemyView;
        enemy.register(enemyView);
        enemyView.setDimension(enemy.getBody().getDimension());
    }

    public void update() {
        enemyView.setPosition(ViewUtils.worldPointToFX(enemy.getBody().getPosition()));
    }
}