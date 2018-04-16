package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.WordText;

/**
 *  Difficulty values.
 */
public enum Difficulty {

    /**
     *  Difficulty values.
     */
    EASY(WordText.EASY), MEDIUM(WordText.MEDIUM_D), HARD(WordText.HARD);

    private final WordText text;

    Difficulty(final WordText text) {
        this.text = text;
    }

    /**
     * @return difficulty as text.
     */
    public WordText asText() {
        return this.text;
    }

}
