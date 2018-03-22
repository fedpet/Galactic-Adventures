package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.TriggerState;

public interface TriggerEntityView extends EntityView {

    /**
     * Used to change the entity view state.
     * 
     * @param state
     *            The state to be associated to the entity view.
     */
    void changeState(TriggerState state);

}
