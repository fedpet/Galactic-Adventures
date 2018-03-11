package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.entities.BasicEnemy;
import javafx.animation.Animation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BasicEnemyView extends ImageView {
    private static final Image IMG_WALK = new Image("/slimeGreen_moving.png");
    private static final int WIDTH = 106, HEIGHT = 64;

    private Animation animation;

    public BasicEnemyView(final BasicEnemy enemy) {
        setImage(IMG_WALK);
        setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
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
