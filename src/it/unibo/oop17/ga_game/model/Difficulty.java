package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.WordText;

/**
 *  Difficulty values.
 */
public enum Difficulty {
    
    EASY(WordText.EASY),
    MEDIUM(WordText.MEDIUM_D),
    HARD(WordText.HARD);
    
    private final WordText text;
    
    private Difficulty (final WordText text) {
        this.text = text;
    }
    
    public WordText asText() {
        return this.text;
    }
    
}
