package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class CoinBronzeView extends AbstractEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;

    public CoinBronzeView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(State.IDLE, justAnImage(new Image("\\tiles\\base_pack\\items\\coinBronze.png")));

        startAnimation(State.IDLE);
    }
}