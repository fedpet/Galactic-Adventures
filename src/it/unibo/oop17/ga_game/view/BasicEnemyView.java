package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.entities.Enemy;
import javafx.scene.image.Image;

public class BasicEnemyView extends EnemyView {

    private static final Image IMG_WALK = new Image("/slimeGreen_moving.png");
    private static final int WIDTH = 106, HEIGHT = 64;

    public BasicEnemyView(Enemy enemy) {
        super(enemy, IMG_WALK, WIDTH, HEIGHT);
    }

}
