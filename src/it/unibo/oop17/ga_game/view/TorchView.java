package it.unibo.oop17.ga_game.view;

import javafx.animation.Animation;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Models a view for a torch.
 */
public class TorchView {

    private final ImageView view = new ImageView(new Image("/torch.png"));
    private static final int WIDTH = 70, HEIGHT = 70;

    /**
     * @param group
     *            The group in which the torch view is added.
     */
    public TorchView(final Group group) {

        final Animation animation = new SpriteAnimation(view, Duration.millis(700), 2,
                WIDTH,
                HEIGHT);
        animation.setCycleCount(Animation.INDEFINITE);
        animation.play();

        group.getChildren().add(view);
    }

}
