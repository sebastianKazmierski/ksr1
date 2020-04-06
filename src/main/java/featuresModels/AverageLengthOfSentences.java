package featuresModels;

import constants.Constants;
import data.Article;

import java.util.*;

public class AverageLengthOfSentences<T> implements FeatureExtractor<T> {
    @Override
    public double extract(Article<T> article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        List<Integer> lengthOfSentence = new ArrayList<>();

        int counter =0;
        for (String word : contentTokensAfterStemming) {
            if (word.equals(".") || word.equals("!") || word.equals("?")) {
                lengthOfSentence.add(counter);
                counter=0;
            }
            if (!Constants.END_WORD_PUNCTUATION.contains(word.substring(word.length() - 1))) {
                counter++;
            }
        }
        return lengthOfSentence.stream().mapToInt(e -> e).average().orElse(0);
    }

    @Override
    public int getNumber() {
        return 7;
    }

    @Override
            public String description() {
            return "Średnia długość zdania";
            }
}
