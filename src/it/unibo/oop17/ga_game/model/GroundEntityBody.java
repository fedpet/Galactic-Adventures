package it.unibo.oop17.ga_game.model;

/**
 * A GroundEntityBody features special handling to detect when its bottom part is touching a solid body.
 */
public interface GroundEntityBody extends EntityBody {
    /**
     * 
     * @return true if the body's bottom part is touching a solid body.
     */
    boolean isOnGround();
}
