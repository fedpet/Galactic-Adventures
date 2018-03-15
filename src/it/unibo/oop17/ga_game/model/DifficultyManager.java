package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Text;

public final class DifficultyManager {
    
    private static int index;
    
    private DifficultyManager() {
    }
    
    public static ConfigData next(final ConfigData data) {
        index = (index + 1) % Difficulty.values().length;
        data.setDifficulty(Difficulty.values()[index]);
        return data;
    }
    
    public static Text getDifficultyText(final ConfigData data) {
        switch (data.getDifficulty()) {
        case EASY:
            return Text.EASY;
        case MEDIUM:
            return Text.MEDIUM_D;
        case HARD:
            return Text.HARD;
        default:
            return Text.MEDIUM_D;
        }
    }
    
}
