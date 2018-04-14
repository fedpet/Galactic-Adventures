package it.unibo.oop17.ga_game.model.entities.events;

import it.unibo.oop17.ga_game.model.entities.Entity;
import it.unibo.oop17.ga_game.model.entities.components.Life;

/**
 * An event used when an @Entity hits another one with a @Weapon.
 */
public class HitEvent extends AbstractEntityEvent {
    private final Entity target;
    private final int damage;

    /**
     * @param source
     *            The entity hitting the other one
     * @param targetEntity
     *            The entity hit
     * @param damageDealt
     *            The damage dealt
     */
    public HitEvent(final Entity source, final Entity targetEntity, final int damageDealt) {
        super(source);
        target = targetEntity;
        damage = damageDealt;
    }

    /**
     * @return The entity hit
     */
    public Entity getTarget() {
        return target;
    }

    /**
     * @return The damage dealt to the target
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @return True if this hit caused the target's death.
     */
    public boolean hasKilled() {
        return target.get(Life.class).isPresent() && target.get(Life.class).get().isDead() && damage > 0;
    }
}
