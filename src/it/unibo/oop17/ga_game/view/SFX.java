package it.unibo.oop17.ga_game.view;

/**
 *  In-game sound effects.
 */
public enum SFX {
    
    MOUSE_ENTERED("/audio/sfx/zap1.wav"),
    MOUSE_CLICKED("/audio/sfx/laser1.wav"),
    ENEMY_DEATH("/audio/sfx/highUp.wav"),
    PLAYER_DAMAGE("/audio/sfx/pepSound1.wav"),
    JUMP("/audio/sfx/phaserUp2.wav"),
    COIN("/audio/sfx/phaserUp7.wav"),
    KEY("/audio/sfx/zapThreeToneUp.wav"),
    LEVER("/audio/sfx/lowThreeTone.wav"),
    SPRING("/audio/sfx/phaserUp1.wav"),
    LOCK("/audio/sfx/phaserUp4.wav"),
    SUCCESS("/audio/sfx/powerUp11.wav");

    private final String path;
    
    private SFX(final String path) {
        this.path = path;
    }
    
    public String getPath() {
        return getClass().getResource(path).toString();
    }
}