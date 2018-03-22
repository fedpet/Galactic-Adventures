package it.unibo.oop17.ga_game.view.entities;

import java.util.HashMap;
import java.util.Map;

import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent.State;
import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
import javafx.geometry.VerticalDirection;
import javafx.scene.Group;

public abstract class AbstractLivingEntityView extends AbstractEntityView implements LivingEntityView {

    private static final double DEATH_FALLING_SPEED = 0.05;

    private final Map<MovementComponent.State, Runnable> animations = new HashMap<>();
    private Point2D pointFromDeath;

    public AbstractLivingEntityView(Group group, Dimension2D dimension) {
        super(group, dimension);
    }

    protected void startAnimation(final MovementComponent.State state) {
        animations.get(state).run();
    }

    protected void mapAnimation(final MovementComponent.State state, final Runnable runnable) {
        animations.put(state, runnable);
    }

    @Override
    public void changeMovement(final MovementComponent.State state) {
        animations.getOrDefault(state, animations.get(State.IDLE)).run();
    }

    @Override
    public Point2D updatePointFromDeath(final Point2D startingPoint) {
        if (pointFromDeath == null) {
            pointFromDeath = startingPoint;
        }
        pointFromDeath = pointFromDeath.subtract(new Point2D(0, DEATH_FALLING_SPEED));
        return pointFromDeath;
    }

    @Override
    public void deathAnimation() {
        flip(VerticalDirection.DOWN);
        changeMovement(MovementComponent.State.IDLE);
    }

}
