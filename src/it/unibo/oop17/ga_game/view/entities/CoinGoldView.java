package it.unibo.oop17.ga_game.view.entities;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class CoinGoldView extends StaticItemView {
    private static final int WIDTH = 70, HEIGHT = 70;

    public CoinGoldView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT), new Image("\\tiles\\base_pack\\items\\coinGold.png"));
    }
}