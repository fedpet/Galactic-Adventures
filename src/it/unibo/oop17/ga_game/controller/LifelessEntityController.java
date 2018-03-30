package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;

public class LifelessEntityController extends AbstractLifelessEntityController<LifelessEntityView> {

    public LifelessEntityController(final Entity entity, final LifelessEntityView entityView) {
        super(entity, entityView);
    }

}
