package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.view.AudioPlayer;
import it.unibo.oop17.ga_game.view.SFX;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Models a @FlyingEnemy view.
 */
public final class FlyingEnemyView extends AbstractLivingEntityView {
    private static final int WIDTH = 112, HEIGHT = 88;
    private static final Image IMG_HURT = new Image("/bee_dead.png");
    private static final double FRAME_DURATION = 700;
    private static final int PAIN_ANIM_DURATION = 300; // ms

    /**
     * @param group
     *            The @Group in which the flying enemy view is added.
     * @param audioplayer
     *            The audio player.
     */
    public FlyingEnemyView(final Group group, final AudioPlayer audioplayer) {
        super(group, new Dimension2D(WIDTH, HEIGHT), audioplayer);

        mapAnimation(CreatureState.IDLE, justAnImage(new Image("/bee.png")));
        mapAnimation(CreatureState.FLYING,
                aSpriteAnimation(new Image("/bee_moving.png"), Duration.millis(FRAME_DURATION), 2));
        mapAnimation(CreatureState.SUFFERING, painAnimation(IMG_HURT, PAIN_ANIM_DURATION, SFX.ENEMY_DAMAGE.getPath()));
        mapAnimation(CreatureState.DEAD, justAnImage(new Image("/bee_dead.png")));

        startAnimation(CreatureState.FLYING);
    }
}
