package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

public final class SlimeEnemyView extends AbstractEntityView {
    private static final int WIDTH = 106, HEIGHT = 64;

    public SlimeEnemyView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(State.WALKING, setAnimation(new Image("/slimeGreen_moving.png"), Duration.millis(700), 2));

        startAnimation(State.WALKING);
    }
}