package it.unibo.oop17.ga_game.model.entities;

import it.unibo.oop17.ga_game.model.entities.components.Brain;
import it.unibo.oop17.ga_game.model.entities.components.EntityBody;
import it.unibo.oop17.ga_game.model.entities.components.LinearLife;
import it.unibo.oop17.ga_game.model.entities.components.MovementComponent;

public abstract class AbstractEnemy extends AbstractEntity implements Enemy {

    private final int damage;

    public AbstractEnemy(final EntityBody body, final Brain brain, final MovementComponent movement, final int damage) {
        super(body);
        this.damage = damage;
        add(brain);
        add(movement);
        add(new LinearLife(5));
    }

    @Override
    public int getDamage() {
        return this.damage;
    }

}
