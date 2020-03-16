package featuresModels;

import constants.Constants;
import data.Article;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class NumberOfWordsWhichAreMultipleTimesInTextInRelationToLengthOfText implements FeatureExtractor {
    @Override
    public double extract(Article article) {
        HashMap<String, Integer> occurrenceCounter = new HashMap<>();
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming()
                .stream().filter(e -> !Constants.END_WORD_PUNCTUATION.contains(e.substring(e.length() - 1))).collect(Collectors.toList());
        for (String word : contentTokensAfterStemming) {
            if (occurrenceCounter.containsKey(word)) {
                Integer numberOfOccurrence = occurrenceCounter.get(word);
                occurrenceCounter.put(word, numberOfOccurrence + 1);
            } else {
                occurrenceCounter.put(word, 1);
            }
        }

        Collection<Integer> values = occurrenceCounter.values();
        long counter = values.stream().filter(e -> e > 1).count();
        return (double) counter / contentTokensAfterStemming.size();
    }
}
