package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.KeyLockType;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

// classe estesa temporanea
public class KeyView extends AbstractTriggerEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;
    private static final String RED_KEY = "/tiles/base_pack/items/keyRed.png";
    private static final String BLUE_KEY = "/tiles/base_pack/items/keyBlue.png";
    private static final String YELLOW_KEY = "/tiles/base_pack/items/keyYellow.png";
    private static final String GREEN_KEY = "/tiles/base_pack/items/keyGreen.png";

    public KeyView(final Group group, final KeyLockType type) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        if (type == KeyLockType.RED) {
            justAnImage(new Image(RED_KEY)).run();
        } else if (type == KeyLockType.BLUE) {
            justAnImage(new Image(BLUE_KEY)).run();
        } else if (type == KeyLockType.YELLOW) {
            justAnImage(new Image(YELLOW_KEY)).run();
        } else if (type == KeyLockType.GREEN) {
            justAnImage(new Image(GREEN_KEY)).run();
        }
    }
}