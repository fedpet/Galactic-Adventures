package it.unibo.oop17.ga_game.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class LanguageLoader {
    
    final private Map<Text, String> langMap;
    
    public LanguageLoader() {
        this.langMap = new HashMap<Text, String>();
    }
    
    public void loadLanguage(final Language lang) {
        String path = null;
        final List<Text> temp1 = new ArrayList<Text>();
        final List<String> temp2 = new ArrayList<String>();
        switch (lang) {
        case ITA:
            path = "res/languages/italian.txt";
            break;
        case ENG:
            path = "res/languages/english.txt";
            break;
        case SPA:
            path = "res/languages/spanish.txt";
            break;
        case DEU:
            path = "res/languages/deutsch.txt";
            break;
        case FRE:
            path = "res/languages/french.txt";
            break;
        default:
            path = "res/languages/english.txt";
            break; 
        }
        Stream<String> stream;
        for (final Text t : Text.values()) {
            temp1.add(t);
        }
        try {
            stream = Files.lines(Paths.get(path));
            stream.forEach(e -> temp2.add(e));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        final Iterator<Text> it1 = temp1.iterator();
        final Iterator<String> it2 = temp2.iterator();
        while (it1.hasNext() && it2.hasNext()) {
            langMap.put(it1.next(), it2.next());
        }
    }
    
    public String getText(final Text text) {
        return ("     " + langMap.get(text));
    }
    
}
