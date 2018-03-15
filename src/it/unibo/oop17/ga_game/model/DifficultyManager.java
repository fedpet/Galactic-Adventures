package it.unibo.oop17.ga_game.model;

public final class DifficultyManager {
    
    private static int index;
    
    private DifficultyManager() {
    }
    
    public static Difficulty next() {
        index = (index + 1) % Difficulty.values().length;
        return Difficulty.values()[index];
    }
    
}
