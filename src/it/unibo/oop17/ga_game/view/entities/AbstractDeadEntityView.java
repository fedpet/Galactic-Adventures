package it.unibo.oop17.ga_game.view.entities;

import java.util.HashMap;
import java.util.Map;

import it.unibo.oop17.ga_game.model.entities.components.DeadState;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;

public class AbstractDeadEntityView extends AbstractEntityView implements DeadEntityView {

    private final Map<DeadState, Runnable> animations = new HashMap<>();

    public AbstractDeadEntityView(Group group, Dimension2D dimension) {
        super(group, dimension);
    }

    protected void startAnimation(final DeadState state) {
        animations.get(state).run();
    }

    protected void mapAnimation(final DeadState state, final Runnable runnable) {
        animations.put(state, runnable);
    }

    @Override
    public void changeState(final DeadState state) {
        animations.getOrDefault(state, animations.get(DeadState.OFF)).run();
    }

}
