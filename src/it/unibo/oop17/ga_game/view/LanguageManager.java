package it.unibo.oop17.ga_game.view;

public final class LanguageManager {
   
    private static int index;
    
    private LanguageManager() {
    }
    
    public static Language next() {
        index = (index + 1) % Language.values().length;
         return Language.values()[index];
    }
}
