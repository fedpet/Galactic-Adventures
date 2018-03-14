package it.unibo.oop17.ga_game.model;

public final class LanguageManager {
   
    private final static String PATH = "configdata.dat";
    
    private LanguageManager() {
    }
    
    public static void next() {
        final ConfigData data = ResourceManager.load(PATH);
        switch (data.getLanguage()) {
        case ITA:
            data.setLanguage(Language.ENG);
            break;
        case ENG:
            data.setLanguage(Language.SPA);
            break;
        case SPA:
            data.setLanguage(Language.FRE);
            break;
        case FRE:
            data.setLanguage(Language.DEU);
            break;
        case DEU:
            data.setLanguage(Language.ITA);
            break;
        default:
            data.setLanguage(Language.ENG);
        }
        ResourceManager.save(data, PATH);
    }
    
    public static Text getLanguageText() {
        switch (ResourceManager.load(PATH).getLanguage()) {
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
