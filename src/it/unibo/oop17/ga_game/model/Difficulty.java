package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.WordText;

/**
 *  Difficulty values.
 */
public enum Difficulty {

    /**
     *  Difficulty values.
     */
    EASY(WordText.EASY, 10), MEDIUM(WordText.MEDIUM_D, 5), HARD(WordText.HARD, 3);

    private final WordText text;
    private final int playerHealth;

    Difficulty(final WordText text, final int playerHealth) {
        this.text = text;
        this.playerHealth = playerHealth;
    }

    /**
     * @return difficulty as text.
     */
    public WordText asText() {
        return this.text;
    }

    /**
     * @return Recommended health level for the player.
     */
    public int playerHealth() {
        return this.playerHealth;
    }
}
