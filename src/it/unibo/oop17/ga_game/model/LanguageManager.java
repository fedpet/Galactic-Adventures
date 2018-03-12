package it.unibo.oop17.ga_game.model;

public class LanguageManager {
   
    private ConfigData data;
    
    public LanguageManager() {
        this.data = ResourceManager.load("configdata.dat");
    }
    
    public void next() {
        this.data = ResourceManager.load("configdata.dat");
        switch (this.data.language) {
        case ITA:
            this.data.language = Language.ENG;
            break;
        case ENG:
            this.data.language = Language.SPA;
            break;
        case SPA:
            this.data.language = Language.FRE;
            break;
        case FRE:
            this.data.language = Language.DEU;
            break;
        case DEU:
            this.data.language = Language.ITA;
            break;
        default:
            this.data.language = Language.ENG;
        }
        ResourceManager.save(data, "configdata.dat");
    }
    
    public Text getLanguageText() {
        switch (this.data.language) {
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
