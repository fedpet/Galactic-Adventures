package it.unibo.oop17.ga_game.view.entities;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public final class PlayerView extends AbstractLivingEntityView {
    private static final int WIDTH = 72, HEIGHT = 97;

    public PlayerView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(CreatureState.IDLE, justAnImage(new Image("/p1_stand.png")));
        mapAnimation(CreatureState.WALKING, setAnimation(new Image("/p1_walk.png"), Duration.millis(700), 10));
        mapAnimation(CreatureState.JUMPING, justAnImage(new Image("/p1_jump.png")));
        mapAnimation(CreatureState.SUFFERING, justAnImage(new Image("/p1_hurt.png")));

        startAnimation(CreatureState.IDLE);
    }
}
