package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.TriggerState;
import it.unibo.oop17.ga_game.model.entities.events.TriggerEvent;
import it.unibo.oop17.ga_game.view.entities.TriggerEntityView;

public class TriggerEntityController extends AbstractLifelessEntityController<TriggerEntityView> {

    public TriggerEntityController(final Entity entity, final TriggerEntityView entityView) {
        super(entity, entityView);
    }

    @Subscribe
    public void trigger(final TriggerEvent event) {
        getEntityView().changeState(TriggerState.ON);
    }

}
