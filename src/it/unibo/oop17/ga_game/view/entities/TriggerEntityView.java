package it.unibo.oop17.ga_game.view.entities;

import it.unibo.oop17.ga_game.model.entities.components.TriggerState;

/**
 * Models an entity view that can be triggered or can trigger a event.
 */
public interface TriggerEntityView extends StateChangingEntityView<TriggerState>, LifelessEntityView {

}
