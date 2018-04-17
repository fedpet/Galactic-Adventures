package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.TriggerState;
import it.unibo.oop17.ga_game.view.AudioPlayer;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Models a @Lever view.
 */
public class LeverView extends AbstractStateChangingEntityView<TriggerState> implements TriggerEntityView {

    private static final int WIDTH = 70, HEIGHT = 70;
    private final AudioPlayer audioplayer;

    /**
     * @param group
     *            The @Group in which the lever view is added.
     * @param activatedFromStart
     *            The initial state of the lever (activated or not) to represent.
     * @param audioplayer
     *            The audio player.
     */
    public LeverView(final Group group, final boolean activatedFromStart, final AudioPlayer audioplayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT));
        this.audioplayer = audioplayer;
        mapAnimation(TriggerState.ACTIVATED, justAnImage(new Image("/switchRight.png")));
        mapAnimation(TriggerState.DEACTIVATED, justAnImage(new Image("/switchLeft.png")));

        if (activatedFromStart) {
            startAnimation(TriggerState.ACTIVATED);
        } else {
            startAnimation(TriggerState.DEACTIVATED);
        }
    }

    @Override
    public final void changeState(final TriggerState state) {
        audioplayer.playSFX(SFX.LEVER.getPath());
        super.changeState(state);
    }
}
