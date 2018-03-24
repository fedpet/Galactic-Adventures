package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.CoinType;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;

public class CoinView extends AbstractEntityView implements LifelessEntityView {
    private static final int WIDTH = 70, HEIGHT = 70;
    private static final String BRONZE_COIN = "/coinBronze.png";
    private static final String SILVER_COIN = "/coinSilver.png";
    private static final String GOLD_COIN = "/coinGold.png";

    public CoinView(final Group group, final CoinType type) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        if (type == CoinType.BRONZE) {
            getView().setImage(new Image(BRONZE_COIN));
        } else if (type == CoinType.SILVER) {
            getView().setImage(new Image(SILVER_COIN));
        } else if (type == CoinType.GOLD) {
            getView().setImage(new Image(GOLD_COIN));
        }
    }
}