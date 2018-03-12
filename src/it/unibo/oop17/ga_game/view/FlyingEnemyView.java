package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.entities.Enemy;
import javafx.scene.image.Image;

public class FlyingEnemyView extends EnemyView {

    private static final Image IMG_WALK = new Image("/bee_moving.png");
    private static final int WIDTH = 112, HEIGHT = 88;

    public FlyingEnemyView(Enemy enemy) {
        super(enemy, IMG_WALK, WIDTH, HEIGHT);
    }

}