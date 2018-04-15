package it.unibo.oop17.ga_game.view;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.application.Platform;

public class LoadLanguage {
    
    private final Map<Language, Map<WordText, String>> languages;
    
    public LoadLanguage() {
        this.languages = new HashMap<Language, Map<WordText, String>>();
        for (final Language l : Language.values()) {
            this.languages.put(l, loadLanguage(l));
        }
    }
    
    private Map<WordText, String> loadLanguage(final Language l) {
        try {
            return  Files.lines(Paths.get("res", "languages", l.toString() + ".txt"))
                    .map(line -> line.split("=", 2))
                    .collect(Collectors.toMap(key -> WordText.valueOf(key[0]), val -> val[1]));
        } catch (IOException e) {
            Platform.exit();
            return null;
        }
    }
    
    public Map<WordText, String> getCurrLang(final Language lang) {
        return this.languages.get(lang);
    }

}
