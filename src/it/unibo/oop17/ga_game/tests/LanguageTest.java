package it.unibo.oop17.ga_game.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.LoadLanguage;
import it.unibo.oop17.ga_game.view.WordText;

/**
 * Tests {@link LoadLanguage}.
 */
public class LanguageTest {

    /**
     * Tests {@link LoadLanguage}.
     * Loading must succeed.
     */
    @Test
    public void testLanguageLoading() {

        for (final Language l : Language.values()) {
            final Map<WordText, String> currLang = new LoadLanguage().getCurrLang(l);
            currLang.entrySet().stream().forEach(e -> assertNotNull(e.getValue()));
            assertTrue(currLang.entrySet().stream().allMatch(e -> e.getValue().length() > 0));
        }

    }

}
