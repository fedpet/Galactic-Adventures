package it.unibo.oop17.ga_game.view;

/**
 * In game backgrounds.
 */
public enum Background {

    /**
     * In game backgrounds.
     */
    BG_GRASSLAND("/bg_grasslands.png"), BG_DESERT("/bg_desert.png"), BG_SHROOM("/bg_shroom.png"), BG_CASTLE("/bg_castle.png");

    private final String path;

    Background(final String path) {
        this.path = path;
    }

    /**
     *  @return the background path.
     */
    public String getPath() {
        return getClass().getResource(path).toString();
    }

}
