package featuresModels;

import data.Article;
import featuresModels.keyWords.NumberOfKeyWords;
import featuresModels.keyWords.WordHolderProvider;
import grouping.Label;

import java.util.List;

public class NumberOfKeyWordsInTenFirstPercentOfText<T extends Label<T>> implements FeatureExtractor<T> {
    private WordHolderProvider<T> wordHolderProvider;
    private NumberOfKeyWords<T> numberOfKeyWords;


    public NumberOfKeyWordsInTenFirstPercentOfText(WordHolderProvider<T> wordHolder, Class<T> tClass) {
        this.wordHolderProvider = wordHolder;
        this.numberOfKeyWords = new NumberOfKeyWords<>(tClass);
    }

    @Override
    public double extract(Article<T> article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        List<String> contentTokensAfterStemmingTenPercent = contentTokensAfterStemming.subList(0, (int) ( contentTokensAfterStemming.size() / 10.0));
        return this.numberOfKeyWords.countAllKeyWords(contentTokensAfterStemmingTenPercent, this.wordHolderProvider.getWordHolder());
    }

    @Override
    public int getNumber() {
        return 1;
    }

    @Override
            public String description() {
                return "Liczba wszystkich słów kluczowych w 10% pierwszych procentach tekstu";
            }
}
