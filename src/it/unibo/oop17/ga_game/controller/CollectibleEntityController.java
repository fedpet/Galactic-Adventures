package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.view.entities.LifelessEntityView;

public class CollectibleEntityController extends AbstractLifelessEntityController<LifelessEntityView> {

    public CollectibleEntityController(Entity entity, LifelessEntityView entityView) {
        super(entity, entityView);
    }

}
