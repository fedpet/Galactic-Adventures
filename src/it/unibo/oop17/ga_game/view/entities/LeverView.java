package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.Lever;
import it.unibo.oop17.ga_game.model.entities.components.TriggerState;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

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

        mapAnimation(TriggerState.ON, justAnImage(new Image("/switchRight.png")));
        mapAnimation(TriggerState.OFF, justAnImage(new Image("/switchLeft.png")));

        if (activatedFromStart) {
            startAnimation(TriggerState.ON);
        } else {
            startAnimation(TriggerState.OFF);
        }
    }
}
