package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.CoinType;
import it.unibo.oop17.ga_game.model.entities.components.DeadState;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class CoinView extends AbstractDeadEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;

    public CoinView(final Group group, final CoinType type) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        if (type == CoinType.BRONZE) {
            mapAnimation(DeadState.ON, justAnImage(new Image("/tiles/base_pack/items/coinBronze.png")));
        } else if (type == CoinType.SILVER) {
            mapAnimation(DeadState.ON, justAnImage(new Image("/tiles/base_pack/items/coinSilver.png")));
        } else if (type == CoinType.GOLD) {
            mapAnimation(DeadState.ON, justAnImage(new Image("/tiles/base_pack/items/coinGold.png")));
        }

        startAnimation(DeadState.ON);
    }
}