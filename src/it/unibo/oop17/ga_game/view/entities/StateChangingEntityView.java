package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.EntityState;

/**
 * Models a view for entities that can change state.
 * 
 * @param <S>
 *            {@link EntityState} type.
 */
public interface StateChangingEntityView<S extends EntityState> extends EntityView {

    /**
     * Used to change the entity view state.
     * 
     * @param state
     *            The {@link EntityState} instance to be associated to the entity view.
     */
    void changeState(S state);

}
