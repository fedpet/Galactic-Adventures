package it.unibo.oop17.ga_game.view.entities;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

/**
 * Models a view for a group of spikes.
 */
public class SpikeView extends AbstractEntityView implements LifelessEntityView {
    private static final int WIDTH = 70, HEIGHT = 35;

    /**
     * @param group
     *            The {@link Group} instance in which the spike view is added.
     */
    public SpikeView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        getView().setImage(new Image("/spikes.png"));
    }
}
