package it.unibo.oop17.ga_game.model;

public final class LanguageManager {
   
    private final static String PATH = "configdata.dat";
    
    private LanguageManager() {
    }
    
    public static void next() {
        final ConfigData data = ResourceManager.load(PATH);
        switch (data.language) {
        case ITA:
            data.language = Language.ENG;
            break;
        case ENG:
            data.language = Language.SPA;
            break;
        case SPA:
            data.language = Language.FRE;
            break;
        case FRE:
            data.language = Language.DEU;
            break;
        case DEU:
            data.language = Language.ITA;
            break;
        default:
            data.language = Language.ENG;
        }
        ResourceManager.save(data, PATH);
    }
    
    public static Text getLanguageText() {
        switch (ResourceManager.load(PATH).language) {
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
