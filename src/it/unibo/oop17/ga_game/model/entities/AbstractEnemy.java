package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.Brain;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;

public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    int damage;

    public AbstractEnemy(EntityBody body, Brain brain, MovementComponent movement, int damage) {
        super(body, brain, movement);
        this.damage = damage;
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

}
