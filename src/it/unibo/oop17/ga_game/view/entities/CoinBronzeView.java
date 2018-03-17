package it.unibo.oop17.ga_game.view.entities;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class CoinBronzeView extends StaticItemView {
    private static final int WIDTH = 70, HEIGHT = 70;

    public CoinBronzeView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT), new Image("\\tiles\\base_pack\\items\\coinBronze.png"));
    }
}