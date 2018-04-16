package it.unibo.oop17.ga_game.view;

/**
 *  Supported languages.
 */
public enum Language {

    /**
     * Italian language.
     */
    ITA(WordText.ITALIAN),

    /**
     * English language.
     */
    ENG(WordText.ENGLISH),

    /**
     * Spanish language.
     */
    SPA(WordText.SPANISH),

    /**
     * Deutsch language.
     */
    DEU(WordText.DEUTSCH),

    /**
     * French language.
     */
    FRE(WordText.FRENCH);

    private final WordText text;

    Language(final WordText text) {
        this.text = text;
    }

    /**
     *  @return the language as text.
     */
    public WordText asText() {
        return this.text;
    }
}
