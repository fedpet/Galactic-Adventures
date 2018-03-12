package it.unibo.oop17.ga_game.model;

public class DifficultyManager {
    
    private ConfigData data;
    
    public DifficultyManager() {
        this.data = ResourceManager.load("configdata.dat");
    }
    
    public void next() {
        this.data = ResourceManager.load("configdata.dat");
        switch (this.data.difficulty) {
        case EASY:
            this.data.difficulty = Difficulty.MEDIUM;
            break;
        case MEDIUM:
            this.data.difficulty = Difficulty.HARD;
            break;
        case HARD:
            this.data.difficulty = Difficulty.EASY;
            break;
        default:
            this.data.difficulty = Difficulty.HARD;
        }
        ResourceManager.save(data, "configdata.dat");
    }
    
    public Text getDifficultyText() {
        switch (this.data.difficulty) {
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
