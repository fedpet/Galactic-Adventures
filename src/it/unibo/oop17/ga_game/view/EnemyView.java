package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.entities.Enemy;
import javafx.animation.Animation;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class EnemyView extends ImageView {

    private Animation animation;
    private final Dimension2D dimension;

    public EnemyView(final Enemy enemy, final Image image, int width, int height) {
        this.dimension = new Dimension2D(width, height);
        changeImage(image);
        animation = new SpriteAnimation(this, Duration.millis(400), 2, 0, 0, width, height);
        setAnimation(animation);
    }

    private void changeImage(final Image img) {
        setImage(img);
        setViewport(new Rectangle2D(0, 0, dimension.getWidth(), dimension.getHeight()));
    }

    private void setAnimation(final Animation anim) {
        animation.stop();
        animation = anim;
        animation.setCycleCount(Animation.INDEFINITE);
        animation.playFromStart();
    }
}
