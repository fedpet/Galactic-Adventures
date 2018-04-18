package it.unibo.oop17.ga_game.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Loads languages.
 */
public class LoadLanguage {

    private final Map<Language, Map<WordText, String>> languages;

    /**
     *  Constructor for LoadLanguage.
     */
    public LoadLanguage() {
        this.languages = new HashMap<Language, Map<WordText, String>>();
        for (final Language l : Language.values()) {
            try {
                this.languages.put(l, loadLanguage(l));
            } catch (IOException e) {
                throw new IllegalArgumentException("Language resource does not exits or it's not valid");
            }
        }
    }

    private Map<WordText, String> loadLanguage(final Language l) throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("/languages/" + l.toString() + ".txt")))) {
            return reader.lines()
                    .map(line -> line.split("=", 2))
                    .collect(Collectors.toMap(key -> WordText.valueOf(key[0]), val -> val[1]));
        }
    }

    /**
     *  Return only the given language.
     *  @param lang
     *          The language to load.
     *  @return the translation.
     */
    public Map<WordText, String> getCurrLang(final Language lang) {
        return this.languages.get(lang);
    }

}
