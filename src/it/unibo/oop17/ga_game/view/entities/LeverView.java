package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.Lever;
import it.unibo.oop17.ga_game.model.entities.components.TriggerState;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

/**
 * Models a {@link Lever} view.
 */
public class LeverView extends AbstractStateChangingEntityView<TriggerState> implements TriggerEntityView {

    private static final int WIDTH = 70, HEIGHT = 70;

    /**
     * @param group
     *            The @Group in which the lever view is added.
     * @param open
     *            The initial state of the lever (activated or not) to represent.
     */
    public LeverView(final Group group, final boolean activatedFromStart) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(TriggerState.ACTIVATED, justAnImage(new Image("/switchRight.png")));
        mapAnimation(TriggerState.DEACTIVATED, justAnImage(new Image("/switchLeft.png")));

        if (activatedFromStart) {
            startAnimation(TriggerState.ACTIVATED);
        } else {
            startAnimation(TriggerState.DEACTIVATED);
        }
    }

    @Override
    public void changeState(final TriggerState state) {
        new AudioClip(SFX.LEVER.getPath()).play();
        super.changeState(state);
    }
}
