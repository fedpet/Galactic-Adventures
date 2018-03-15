package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.ConfigData;

public final class LanguageManager {
   
    private static int index;
    
    private LanguageManager() {
    }
    
    public static ConfigData next(final ConfigData data) {
        index = (index + 1) % Language.values().length;
        data.setLanguage(Language.values()[index]);
        return data;
    }
    
    public static Text getLanguageText(final ConfigData data) {
        switch (data.getLanguage()) {
        case ITA:
            return Text.ITALIAN;
        case ENG:
            return Text.ENGLISH;
        case SPA:
            return Text.SPANISH;
        case FRE:
            return Text.FRENCH;
        case DEU:
            return Text.DEUTSCH;
        default:
            return Text.ENGLISH;
        }
    }
}
