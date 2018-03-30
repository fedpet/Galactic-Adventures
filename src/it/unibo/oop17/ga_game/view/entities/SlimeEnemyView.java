package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.SlimeEnemy;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 * Models a {@link SlimeEnemy} view.
 */
public final class SlimeEnemyView extends AbstractLivingEntityView {
    private static final int WIDTH = 106, HEIGHT = 64;

    /**
     * @param group
     *            The @Group in which the player view is added.
     */
    public SlimeEnemyView(final Group group) {
        super(group, new Dimension2D(WIDTH, HEIGHT));

        mapAnimation(CreatureState.IDLE, justAnImage(new Image("/slimeGreen.png")));
        mapAnimation(CreatureState.WALKING, aSpriteAnimation(new Image("/slimeGreen_moving.png"), Duration.millis(700), 2));
        mapAnimation(CreatureState.DEAD, justAnImage(new Image("/slimeGreen_dead.png")));

        startAnimation(CreatureState.WALKING);
    }
}