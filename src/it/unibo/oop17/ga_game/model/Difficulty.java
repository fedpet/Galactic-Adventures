package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Text;

public enum Difficulty {
    
    EASY(Text.EASY),
    MEDIUM(Text.MEDIUM_D),
    HARD(Text.HARD);
    
    private final Text text;
    
    private Difficulty (final Text text) {
        this.text = text;
    }
    
    public Text asText() {
        return this.text;
    }
    
}
