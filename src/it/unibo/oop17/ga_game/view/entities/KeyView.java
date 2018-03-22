package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.KeyLockType;
import it.unibo.oop17.ga_game.model.entities.components.DeadState;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class KeyView extends AbstractDeadEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;

    public KeyView(final Group group, final KeyLockType type) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        if (type == KeyLockType.RED) {
            mapAnimation(DeadState.ON, justAnImage(new Image("/tiles/base_pack/items/keyRed.png")));
        } else if (type == KeyLockType.BLUE) {
            mapAnimation(DeadState.ON, justAnImage(new Image("/tiles/base_pack/items/keyBlue.png")));
        } else if (type == KeyLockType.YELLOW) {
            mapAnimation(DeadState.ON, justAnImage(new Image("/tiles/base_pack/items/keyYellow.png")));
        } else if (type == KeyLockType.GREEN) {
            mapAnimation(DeadState.ON, justAnImage(new Image("/tiles/base_pack/items/keyGreen.png")));
        }

        startAnimation(DeadState.ON);
    }
}