package it.unibo.oop17.ga_game.model.entities.components;

/**
 * Models an entity life as a counter of health points (int number).
 */
public interface Life extends EntityComponent {
    /**
     * @return
     *         Current amount of health points.
     */
    int getHealthPoints();

    /**
     * 
     * @return Maximum amount of health points for this entity.
     */
    int getMaxHealthPoints();

    /**
     * 
     * @param damageAmount
     *            Amount of damage (in health points) to inflict to this Life
     */
    void hurt(int damageAmount);

    /**
     * 
     * @param restoreAmount
     *            Amount of health points to restore.
     */
    void heal(int restoreAmount);

    /**
     * 
     * @return True if this Life is.. alive.
     */
    default boolean isAlive() {
        return getHealthPoints() > 0;
    }

    /**
     * 
     * @return True if this Life is ended.
     */
    default boolean isDead() {
        return !isAlive();
    }
}
