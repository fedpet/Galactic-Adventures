package it.unibo.oop17.ga_game.view;

/**
 *  Supported languages.
 */
public enum Language {
    
    ITA(WordText.ITALIAN),
    ENG(WordText.ENGLISH),
    SPA(WordText.SPANISH),
    DEU(WordText.DEUTSCH),
    FRE(WordText.FRENCH);
    
    private final WordText text;
    
    private Language (final WordText text) {
        this.text = text;
    }
    
    public WordText asText() {
        return this.text;
    }
}
