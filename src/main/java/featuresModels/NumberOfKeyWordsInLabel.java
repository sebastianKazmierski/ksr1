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
    public int getNumber() {
        int index=0;
        Label[] enumConstants = this.label.getClass().getEnumConstants();
        for (int i = 0; i < enumConstants.length; i++) {
            if (this.label == enumConstants[i]) {
                index = i + 1;
                break;
            }
        }
        return 11 + index;
    }

    @Override
            public String description() {
                return "Ilość słów kluczowych dla " + this.label;
            }
}
