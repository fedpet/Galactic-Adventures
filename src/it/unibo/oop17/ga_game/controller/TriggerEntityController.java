package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.TriggerState;
import it.unibo.oop17.ga_game.model.entities.events.TriggeredEvent;
import it.unibo.oop17.ga_game.view.entities.TriggerEntityView;

/**
 * Translates view input to model input and updates the view for entities with @TriggerEntityView.
 */
public class TriggerEntityController extends AbstractLifelessEntityController<TriggerEntityView> {

    /**
     * @param entity
     *            The @Entity to control.
     * @param entityView
     *            The @TriggerEntityView to update.
     */
    public TriggerEntityController(final Entity entity, final TriggerEntityView entityView) {
        super(entity, entityView);
    }

    /**
     * It manages the trigger behavior at a @TriggeredEvent signal.
     * 
     * @param event
     *            The @TriggeredEvent to listen to.
     */
    @Subscribe
    public void trigger(final TriggeredEvent event) {
        getEntityView().changeState(TriggerState.ACTIVATED);
    }

}
