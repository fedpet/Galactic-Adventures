package it.unibo.oop17.ga_game.model.entities.components;

import it.unibo.oop17.ga_game.model.entities.SlimeEnemy;

public class PlayerBrain extends AbstractBrain {
    @Override
    public void update(final double dt) {

    }

    @Override
    public void beginContact(final EntityBody other) {
        // TODO refactor this mess
        other.getOwner().ifPresent(owner -> {
            if (other.getOwner().get() instanceof SlimeEnemy) {
                other.getOwner().get().getLife().hurt(10);
            }
        });
    }

    @Override
    public void endContact(final EntityBody other) {

    }
}
