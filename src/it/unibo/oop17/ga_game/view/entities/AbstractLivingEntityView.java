package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.view.ViewUtils;
import javafx.geometry.Dimension2D;
import javafx.geometry.HorizontalDirection;
import javafx.geometry.Point2D;
import javafx.geometry.VerticalDirection;
import javafx.scene.Group;

/**
 * Base class for @LivingEntityView.
 */
public abstract class AbstractLivingEntityView extends AbstractStateChangingEntityView<CreatureState>
        implements LivingEntityView {

    private static final double DEATH_FALLING_SPEED = 0.05;
    private static final double DEATH_TIME = 60;

    private int deathTimeCount;
    private Point2D pointFromDeath;

    /**
     * @param group
     *            The @Group in which the entity view is added.
     * @param dimension
     *            The entity view dimension.
     */
    public AbstractLivingEntityView(final Group group, final Dimension2D dimension) {
        super(group, dimension);
    }

    @Override
    public void changeFaceDirection(final HorizontalDirection direction) {
        getView().setScaleX(direction == HorizontalDirection.RIGHT ? 1 : -1);
    }

    @Override
    public void deathAnimation(final Point2D startingPoint) {
        if (pointFromDeath == null) {
            pointFromDeath = startingPoint;
            flip(VerticalDirection.DOWN);
        }
        pointFromDeath = pointFromDeath.subtract(new Point2D(0, DEATH_FALLING_SPEED));
        super.setPosition(ViewUtils.worldPointToFX(pointFromDeath));
        manageDespawn();
    }

    private void manageDespawn() {
        deathTimeCount++;
        if (deathTimeCount == DEATH_TIME) {
            remove();
        }
    }

    private void flip(final VerticalDirection direction) {
        getView().setScaleY(direction == VerticalDirection.UP ? 1 : -1);
    }

}
