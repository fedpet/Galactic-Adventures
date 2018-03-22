package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public final class FlyingEnemyView extends AbstractLivingEntityView {
    private static final int WIDTH = 112, HEIGHT = 88;

    public FlyingEnemyView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(State.IDLE, justAnImage(new Image("/bee.png")));
        mapAnimation(State.FLYING, setAnimation(new Image("/bee_moving.png"), Duration.millis(700), 2));

        startAnimation(State.FLYING);
    }
}