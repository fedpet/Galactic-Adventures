package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.TriggerState;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class LeverView extends AbstractStateChangingEntityView<TriggerState> implements TriggerEntityView {

    private static final int WIDTH = 70, HEIGHT = 70;

    public LeverView(final Group group, final boolean activatedFromStart) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(TriggerState.ON, justAnImage(new Image("/tiles/base_pack/items/switchRight.png")));
        mapAnimation(TriggerState.OFF, justAnImage(new Image("/tiles/base_pack/items/switchLeft.png")));

        if (activatedFromStart) {
            startAnimation(TriggerState.ON);
        } else {
            startAnimation(TriggerState.OFF);
        }
    }
}
