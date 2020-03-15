package convertDataModule;

import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;

import java.util.List;
import java.util.stream.Collectors;

public class Stemmer {
    private static final SnowballStemmer snowballStemmer = new englishStemmer();

    public static String stem(String word) {
        snowballStemmer.setCurrent(word.toLowerCase());
        snowballStemmer.stem();
        return snowballStemmer.getCurrent();
    }

    public static List<String> stem(List<String> words) {
        return  words.stream()
                .map(Stemmer::stem)
                .collect(Collectors.toList());
    }

}
