package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public final class PlayerView extends AbstractEntityView {
    private static final int WIDTH = 72, HEIGHT = 97;

    public PlayerView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(State.IDLE, justAnImage(new Image("/p1_stand.png")));
        mapAnimation(State.WALKING, setAnimation(new Image("/p1_walk.png"), Duration.millis(700), 10));
        mapAnimation(State.JUMPING, justAnImage(new Image("/p1_jump.png")));

        startAnimation(State.IDLE);
    }
}
