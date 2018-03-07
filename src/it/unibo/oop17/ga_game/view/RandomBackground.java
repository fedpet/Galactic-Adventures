package it.unibo.oop17.ga_game.view;

import java.util.concurrent.ThreadLocalRandom;

public class RandomBackground {
    
    final private String BackgroundPath;
    
    public RandomBackground() {
        final int randomNum = ThreadLocalRandom.current().nextInt(0, 3 + 1);
        String backgroundPath = null;
        
        switch (randomNum) {
        case 0:
            backgroundPath = "res/Tiles/Mushroom expansion/Backgrounds/bg_castle.png";
            break;
        case 1:
            backgroundPath = "res/Tiles/Mushroom expansion/Backgrounds/bg_desert.png";
            break;
        case 2:
            backgroundPath = "res/Tiles/Mushroom expansion/Backgrounds/bg_grasslands.png";
            break;
        case 3:
            backgroundPath = "res/Tiles/Mushroom expansion/Backgrounds/bg_shroom.png";
            break;
        default:
            backgroundPath = "res/Tiles/Mushroom expansion/Backgrounds/bg_castle.png";
        }
        
        this.BackgroundPath = backgroundPath;
        
    }
    
    public String getBackgroundPath() {
        return this.BackgroundPath;
    }

}