package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public abstract class StaticItemView extends AbstractEntityView {

    public StaticItemView(final Group group, final Dimension2D dimension, final Image image) {
        super(group, dimension);

        mapAnimation(State.IDLE, justAnImage(image));

        startAnimation(State.IDLE);
    }
}