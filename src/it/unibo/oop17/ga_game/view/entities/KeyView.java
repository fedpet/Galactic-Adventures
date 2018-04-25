package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.KeyType;
import it.unibo.oop17.ga_game.view.AudioPlayer;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Models a view for a key.
 */
public class KeyView extends AbstractEntityView implements LifelessEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;
    private static final String RED_KEY = "/keyRed.png";
    private static final String BLUE_KEY = "/keyBlue.png";
    private static final String YELLOW_KEY = "/keyYellow.png";
    private static final String GREEN_KEY = "/keyGreen.png";
    private final AudioPlayer audioplayer;

    /**
     * @param group
     *            The {@link Group} instance in which the key view is added.
     * @param type
     *            The key type to represent (RED, BLUE, YELLOW or GREEN).
     * @param audioplayer
     *            The related {@link AudioPlayer} instance.
     */
    public KeyView(final Group group, final KeyType type, final AudioPlayer audioplayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        this.audioplayer = audioplayer;
        if (type == KeyType.RED) {
            getView().setImage(new Image(RED_KEY));
        } else if (type == KeyType.BLUE) {
            getView().setImage(new Image(BLUE_KEY));
        } else if (type == KeyType.YELLOW) {
            getView().setImage(new Image(YELLOW_KEY));
        } else if (type == KeyType.GREEN) {
            getView().setImage(new Image(GREEN_KEY));
        }
    }

    @Override
    public final void remove() {
        audioplayer.playSFX(SFX.KEY.getPath());
        super.remove();
    }
}
