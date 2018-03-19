package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class LockView extends AbstractEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;

    public LockView(final Group group, final KeyLockType type) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        if (type == KeyLockType.RED) {
            mapAnimation(State.IDLE, justAnImage(new Image("/tiles/base_pack/tiles/lock_red.png")));
        } else if (type == KeyLockType.BLUE) {
            mapAnimation(State.IDLE, justAnImage(new Image("/tiles/base_pack/tiles/lock_blue.png")));
        } else if (type == KeyLockType.YELLOW) {
            mapAnimation(State.IDLE, justAnImage(new Image("/tiles/base_pack/tiles/lock_yellow.png")));
        } else if (type == KeyLockType.GREEN) {
            mapAnimation(State.IDLE, justAnImage(new Image("/tiles/base_pack/tiles/lock_green.png")));
        }

        startAnimation(State.IDLE);
    }
}