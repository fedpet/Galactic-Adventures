package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.view.AudioPlayer;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Models a view for a slime enemy.
 */
public final class SlimeEnemyView extends AbstractLivingEntityView {
    private static final int WIDTH = 106, HEIGHT = 64;
    private static final Image IMG_HURT = new Image("/slimeGreen_dead.png");
    private static final double FRAME_DURATION = 700;

    /**
     * @param group
     *            The {@link Group} instance in which the player view is added.
     * @param audioplayer
     *            The related {@link AudioPlayer} instance.
     */
    public SlimeEnemyView(final Group group, final AudioPlayer audioplayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT), audioplayer);

        mapAnimation(CreatureState.IDLE, justAnImage(new Image("/slimeGreen.png")));
        mapAnimation(CreatureState.WALKING,
                aSpriteAnimation(new Image("/slimeGreen_moving.png"), Duration.millis(FRAME_DURATION), 2));
        mapAnimation(CreatureState.SUFFERING,
                painAnimation(IMG_HURT, SFX.ENEMY_DAMAGE.getPath(), CreatureState.WALKING));
        mapAnimation(CreatureState.DEAD, justAnImage(new Image("/slimeGreen_dead.png")));

        startAnimation(CreatureState.WALKING);
    }
}
