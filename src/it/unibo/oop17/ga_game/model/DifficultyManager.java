package it.unibo.oop17.ga_game.model;

public final class DifficultyManager {
    
    private final static String PATH = "configdata.dat";
    
    private DifficultyManager() {
    }
    
    public static void next() {
        final ConfigData data = ResourceManager.load(PATH);
        switch (data.getDifficulty()) {
        case EASY:
            data.setDifficulty(Difficulty.MEDIUM);
            break;
        case MEDIUM:
            data.setDifficulty(Difficulty.HARD);
            break;
        case HARD:
            data.setDifficulty(Difficulty.EASY);
            break;
        default:
            data.setDifficulty(Difficulty.HARD);
        }
        ResourceManager.save(data, PATH);
    }
    
    public static Text getDifficultyText() {
        switch (ResourceManager.load(PATH).getDifficulty()) {
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
