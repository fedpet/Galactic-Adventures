package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.KeyType;
import it.unibo.oop17.ga_game.view.AudioPlayer;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Models a lock view.
 */
public class LockView extends AbstractEntityView implements LifelessEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;
    private static final String RED_LOCK = "/lock_red.png";
    private static final String BLUE_LOCK = "/lock_blue.png";
    private static final String YELLOW_LOCK = "/lock_yellow.png";
    private static final String GREEN_LOCK = "/lock_green.png";
    private final AudioPlayer audioplayer;

    /**
     * @param group
     *            The {@link Group} instance in which the lock view is added.
     * @param keyType
     *            The related key type to represent (RED, BLUE, YELLOW or GREEN).
     * @param audioplayer
     *            The related {@link AudioPlayer} instance.
     */
    public LockView(final Group group, final KeyType keyType, final AudioPlayer audioplayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        this.audioplayer = audioplayer;
        if (keyType == KeyType.RED) {
            getView().setImage(new Image(RED_LOCK));
        } else if (keyType == KeyType.BLUE) {
            getView().setImage(new Image(BLUE_LOCK));
        } else if (keyType == KeyType.YELLOW) {
            getView().setImage(new Image(YELLOW_LOCK));
        } else if (keyType == KeyType.GREEN) {
            getView().setImage(new Image(GREEN_LOCK));
        }
    }

    @Override
    public final void remove() {
        audioplayer.playSFX(SFX.LOCK.getPath());
        super.remove();
    }
}
