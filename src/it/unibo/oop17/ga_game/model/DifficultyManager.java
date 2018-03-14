package it.unibo.oop17.ga_game.model;

public final class DifficultyManager {
    
    private final static String PATH = "configdata.dat";
    
    private DifficultyManager() {
    }
    
    public static void next() {
        final ConfigData data = ResourceManager.load(PATH);
        switch (data.difficulty) {
        case EASY:
            data.difficulty = Difficulty.MEDIUM;
            break;
        case MEDIUM:
            data.difficulty = Difficulty.HARD;
            break;
        case HARD:
            data.difficulty = Difficulty.EASY;
            break;
        default:
            data.difficulty = Difficulty.HARD;
        }
        ResourceManager.save(data, PATH);
    }
    
    public static Text getDifficultyText() {
        switch (ResourceManager.load(PATH).difficulty) {
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
