package it.unibo.oop17.ga_game.view;

public enum Background {
    
    BG_GRASSLAND("/bg_grasslands.png"),
    BG_DESERT("/bg_desert.png"),
    BG_SHROOM("/bg_shroom.png"),
    BG_CASTLE("/bg_castle.png");
    
    private final String path;
    
    private Background(final String path) {
        this.path = path;
    }
        
    public String getPath() {
        return getClass().getResource(path).toString();
    }

}
