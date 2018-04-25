package it.unibo.oop17.ga_game.view.entities;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Models a view for a moving platform.
 */
public class MovingPlatformView extends AbstractEntityView implements LifelessEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;

    /**
     * @param group
     *            The {@link Group} instance in which the moving platform view is added.
     */
    public MovingPlatformView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        getView().setImage(new Image("/stone.png"));
    }
}
