package it.unibo.oop17.ga_game.view;

import javafx.scene.Group;
import javafx.scene.image.Image;

public final class FlyingEnemyView extends EnemyView {
    private static final int WIDTH = 112, HEIGHT = 88;

    public FlyingEnemyView(final Group group) {
        super(group, WIDTH, HEIGHT, new Image("/bee_moving.png"));
    }



}