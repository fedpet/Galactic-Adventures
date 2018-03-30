package it.unibo.oop17.ga_game.view;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomBackground {
    
    private static final List<String> BACKGROUNDS = Arrays.asList(
            "/bg_castle.png",
            "/bg_grasslands.png",
            "/bg_shroom.png",
            "/bg_desert.png");
    
    /**
     *  @return random background image.
     */
    public String getBackgroundPath() {
        return BACKGROUNDS.get(new Random().nextInt(BACKGROUNDS.size()));
    }
}