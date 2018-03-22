package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.KeyLockType;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

// classe estesa temporanea
public class LockView extends AbstractTriggerEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;
    private static final String RED_LOCK = "/tiles/base_pack/tiles/lock_red.png";
    private static final String BLUE_LOCK = "/tiles/base_pack/tiles/lock_blue.png";
    private static final String YELLOW_LOCK = "/tiles/base_pack/tiles/lock_yellow.png";
    private static final String GREEN_LOCK = "/tiles/base_pack/tiles/lock_green.png";

    public LockView(final Group group, final KeyLockType type) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        if (type == KeyLockType.RED) {
            justAnImage(new Image(RED_LOCK)).run();
        } else if (type == KeyLockType.BLUE) {
            justAnImage(new Image(BLUE_LOCK)).run();
        } else if (type == KeyLockType.YELLOW) {
            justAnImage(new Image(YELLOW_LOCK)).run();
        } else if (type == KeyLockType.GREEN) {
            justAnImage(new Image(GREEN_LOCK)).run();
        }
    }
}