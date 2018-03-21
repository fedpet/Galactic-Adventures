package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;

public class LevelFinishedEvent extends AbstractEntityEvent {

    public LevelFinishedEvent(Entity source) {
        super(source);
    }


}
