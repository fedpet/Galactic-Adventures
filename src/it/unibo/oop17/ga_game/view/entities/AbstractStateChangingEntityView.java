package it.unibo.oop17.ga_game.view.entities;

import java.util.HashMap;
import java.util.Map;

import it.unibo.oop17.ga_game.model.entities.components.GenericState;
import javafx.geometry.Dimension2D;
import javafx.scene.Group;

public abstract class AbstractStateChangingEntityView<S extends GenericState> extends AbstractEntityView
        implements StateChangingEntityView<S> {

    private final Map<S, Runnable> animations = new HashMap<>();

    public AbstractStateChangingEntityView(final Group group, final Dimension2D dimension) {
        super(group, dimension);
    }

    protected void startAnimation(final S state) {
        animations.get(state).run();
    }

    protected void mapAnimation(final S state, final Runnable runnable) {
        animations.put(state, runnable);
    }
    
    @Override
    public void changeState(final S state) {
        if (animations.containsKey(state)) {
            animations.get(state).run();
        }
    }
    
}
