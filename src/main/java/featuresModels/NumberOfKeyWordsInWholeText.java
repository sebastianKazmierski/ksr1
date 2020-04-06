package featuresModels;

import data.Article;
import featuresModels.keyWords.NumberOfKeyWords;
import featuresModels.keyWords.WordHolderProvider;
import grouping.Label;

import java.util.List;

public class NumberOfKeyWordsInWholeText<T extends Label<T>> implements FeatureExtractor<T> {
    private WordHolderProvider<T> wordHolderProvider;
    NumberOfKeyWords<T> numberOfKeyWords;

    public NumberOfKeyWordsInWholeText(WordHolderProvider<T> wordHolder, Class<T> tClass) {
        this.wordHolderProvider = wordHolder;
        this.numberOfKeyWords = new NumberOfKeyWords<>(tClass);
    }

    @Override
    public double extract(Article<T> article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        return this.numberOfKeyWords.countAllKeyWords(contentTokensAfterStemming, this.wordHolderProvider.getWordHolder());
    }

    @Override
    public int getNumber() {
        return 2;
    }

    @Override
            public String description() {
                return "Liczba wszystkich słów kluczowych w całym tekście";
            }
}
