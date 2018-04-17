package it.unibo.oop17.ga_game.view;

/**
 *  In-game sound effects.
 */
public enum SFX {

    /**
     *  Mouse entered sound effect.
     */
    MOUSE_ENTERED("/audio/sfx/zap1.wav"),

    /**
     *  Mouse clicked sound effect.
     */
    MOUSE_CLICKED("/audio/sfx/laser1.wav"),

    /**
     *  Enemy death sound effect.
     */
    DEATH("/audio/sfx/highDown.wav"),

    /**
     *  Enemy damage sound effect.
     */
    ENEMY_DAMAGE("/audio/sfx/highUp.wav"),

    /**
     *  Player damage sound effect.
     */
    PLAYER_DAMAGE("/audio/sfx/pepSound1.wav"),

    /**
     *  Jump sound effect.
     */
    JUMP("/audio/sfx/phaserUp2.wav"),
 
    /**
     *  Coin collected sound effect.
     */
    COIN("/audio/sfx/phaserUp7.wav"),

    /**
     *  key collected sound effect.
     */
    KEY("/audio/sfx/zapThreeToneUp.wav"),

    /**
     *  Lever activated sound effect.
     */
    LEVER("/audio/sfx/lowThreeTone.wav"),

    /**
     *  Spring sound effect.
     */
    SPRING("/audio/sfx/phaserUp1.wav"),

    /**
     *  Lock "unlocked" sound effect.
     */
    LOCK("/audio/sfx/phaserUp4.wav");

    private final String path;

    SFX(final String path) {
        this.path = path;
    }

    /**
     *  @return the sound effect path.
     */
    public String getPath() {
        return getClass().getResource(path).toString();
    }
}
