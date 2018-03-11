package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.entities.FlyingEnemy;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class FlyingEnemyView extends ImageView {
    private static final Image IMG_FLY = new Image("/bee_moving.png");
    private static final int WIDTH = 112, HEIGHT = 88;

    private Animation animation;

    public FlyingEnemyView(final FlyingEnemy enemy) {
        changeImage(IMG_FLY);
        animation = new SpriteAnimation(this, Duration.millis(400), 2, 0, 0, WIDTH, HEIGHT);
        setAnimation(animation);
    }

    private void changeImage(final Image img) {
        setImage(img);
        setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
    }

    private void setAnimation(final Animation anim) {
        animation.stop();
        animation = anim;
        animation.setCycleCount(Animation.INDEFINITE);
        animation.playFromStart();
    }
}
