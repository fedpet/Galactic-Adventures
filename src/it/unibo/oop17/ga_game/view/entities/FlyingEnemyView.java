package it.unibo.oop17.ga_game.view.entities;

import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Models a @FlyingEnemy view.
 */
public final class FlyingEnemyView extends AbstractLivingEntityView {
    private static final int WIDTH = 112, HEIGHT = 88;
    private static final double FRAME_DURATION = 700;

    /**
     * @param group
     *            The @Group in which the flying enemy view is added.
     */
    public FlyingEnemyView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(CreatureState.IDLE, justAnImage(new Image("/bee.png")));
        mapAnimation(CreatureState.FLYING,
                aSpriteAnimation(new Image("/bee_moving.png"), Duration.millis(FRAME_DURATION), 2));
        mapAnimation(CreatureState.DEAD, justAnImage(new Image("/bee_dead.png")));

        startAnimation(CreatureState.FLYING);
    }
}
