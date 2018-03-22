package it.unibo.oop17.ga_game.view.entities;

import java.util.HashMap;
import java.util.Map;

import it.unibo.oop17.ga_game.model.entities.components.TriggerState;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;

public class AbstractTriggerEntityView extends AbstractEntityView implements TriggerEntityView {

    private final Map<TriggerState, Runnable> animations = new HashMap<>();

    public AbstractTriggerEntityView(Group group, Dimension2D dimension) {
        super(group, dimension);
    }

    protected void startAnimation(final TriggerState state) {
        animations.get(state).run();
    }

    protected void mapAnimation(final TriggerState state, final Runnable runnable) {
        animations.put(state, runnable);
    }

    @Override
    public void changeState(final TriggerState state) {
        animations.getOrDefault(state, animations.get(TriggerState.OFF)).run();
    }

}
