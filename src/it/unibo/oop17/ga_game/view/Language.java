package it.unibo.oop17.ga_game.view;

public enum Language {
    
    ITA(Text.ITALIAN),
    ENG(Text.ENGLISH),
    SPA(Text.SPANISH),
    DEU(Text.DEUTSCH),
    FRE(Text.FRENCH);
    
    private final Text text;
    
    private Language (final Text text) {
        this.text = text;
    }
    
    public Text asText() {
        return text;
    }
}
