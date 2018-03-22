package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.DeadState;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class LeverView extends AbstractDeadEntityView {

    private static final int WIDTH = 70, HEIGHT = 70;

    public LeverView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(DeadState.ON, justAnImage(new Image("/tiles/base_pack/items/switchRight.png")));
        mapAnimation(DeadState.OFF, justAnImage(new Image("/tiles/base_pack/items/switchLeft.png")));

        startAnimation(DeadState.OFF);
    }

}
