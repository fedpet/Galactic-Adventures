package it.unibo.oop17.ga_game.debug;

import it.unibo.oop17.ga_game.model.ResetSave;

public final class CleanSave {
    
    private CleanSave() {
    }
    
    public static void main(final String[] args) {
        ResetSave.defaultOptions();
        System.out.println("Default Options restored on gameconfig.cfg");
        ResetSave.resetProgress();
        System.out.println("Game progress restored on gamedata.save");
    }
}