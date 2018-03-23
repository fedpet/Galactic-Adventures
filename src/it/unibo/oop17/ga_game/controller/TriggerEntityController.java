package it.unibo.oop17.ga_game.controller;

import com.google.common.eventbus.Subscribe;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.TriggerComponent;
import it.unibo.oop17.ga_game.model.entities.components.TriggerState;
import it.unibo.oop17.ga_game.model.entities.components.TriggerableComponent;
import it.unibo.oop17.ga_game.model.entities.events.TriggerEvent;
import it.unibo.oop17.ga_game.view.entities.TriggerEntityView;

public class TriggerEntityController extends AbstractLifelessEntityController<TriggerEntityView> {

    public TriggerEntityController(final Entity entity, final TriggerEntityView entityView) {
        super(entity, entityView);
    }

    @Subscribe
    public void triggered(final TriggerEvent event) {
        getEntity().get(TriggerableComponent.class).ifPresent(x -> {
            if (event.getPassword().equals(x.getPassword())) {
                getEntityView().changeState(TriggerState.ON);
            }
        });
        getEntity().get(TriggerComponent.class).ifPresent(x -> {
            if (event.getPassword().equals(x.getPassword())) {
                getEntityView().changeState(TriggerState.ON);
            }
        });
    }

}
