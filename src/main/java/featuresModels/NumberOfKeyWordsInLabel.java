package featuresModels;

import data.Article;
import featuresModels.keyWords.NumberOfKeyWords;
import featuresModels.keyWords.WordHolderProvider;
import grouping.Label;

import java.util.HashMap;
import java.util.List;

public class NumberOfKeyWordsInLabel<T extends Label<T>> implements FeatureExtractor<T> {
    private T label;
    private WordHolderProvider<T> wordHolderProvider;
    private NumberOfKeyWords<T> numberOfKeyWords;

    public NumberOfKeyWordsInLabel(T label, WordHolderProvider<T> wordHolder, Class<T> tClass) {
        this.label = label;
        this.wordHolderProvider = wordHolder;
        this.numberOfKeyWords = new NumberOfKeyWords<T>(tClass);

    }

    @Override
    public double extract(Article<T> article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        List<String> contentTokensAfterStemmingTenPercent = contentTokensAfterStemming.subList(0, (int) (contentTokensAfterStemming.size() / 10.0));
        HashMap<T, Integer> placeOccurrenceMap = this.numberOfKeyWords.count(contentTokensAfterStemmingTenPercent, this.wordHolderProvider.getWordHolder());
        return placeOccurrenceMap.get(this.label);
    }

        @Override
            public String description() {
                return "Ilość słów kluczowych dla " + this.label;
            }
}
