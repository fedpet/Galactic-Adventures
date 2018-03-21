package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class DoorView extends AbstractEntityView {
    private static final int WIDTH = 130, HEIGHT = 203;

    public DoorView(final Group group, final boolean open) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        if (open) {
            mapAnimation(State.IDLE, justAnImage(new Image("/tiles/base_pack/tiles/door_open.png")));
        } else {
            mapAnimation(State.IDLE, justAnImage(new Image("/tiles/base_pack/tiles/door_closed.png")));
        }

        startAnimation(State.IDLE);
    }
}