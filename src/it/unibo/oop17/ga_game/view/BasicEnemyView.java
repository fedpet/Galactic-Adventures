package it.unibo.oop17.ga_game.view;

import javafx.scene.Group;
import javafx.scene.image.Image;

public final class BasicEnemyView extends EnemyView {
    private static final int WIDTH = 106, HEIGHT = 64;

    public BasicEnemyView(final Group group) {
        super(group, WIDTH, HEIGHT, new Image("/slimeGreen_moving.png"));
    }


}