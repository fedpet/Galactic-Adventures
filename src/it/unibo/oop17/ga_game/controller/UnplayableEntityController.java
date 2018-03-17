package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.view.entities.EntityView;

public class UnplayableEntityController extends AbstractEntityController {

    public UnplayableEntityController(final Entity entity, final EntityView entityView) {
        super(entity, entityView);
    }

}
