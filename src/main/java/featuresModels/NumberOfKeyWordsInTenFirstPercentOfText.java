package featuresModels;

import data.Article;
import featuresModels.keyWords.WordHolder;
import featuresModels.keyWords.NumberOfKeyWords;
import grouping.Label;

import java.util.List;

public class NumberOfKeyWordsInTenFirstPercentOfText<T extends Label<T>> implements FeatureExtractor<T> {
    private WordHolder<T> wordHolder;
    private NumberOfKeyWords<T> numberOfKeyWords;


    public NumberOfKeyWordsInTenFirstPercentOfText(WordHolder<T> wordHolder, Class<T> tClass) {
        this.wordHolder = wordHolder;
        this.numberOfKeyWords = new NumberOfKeyWords<>(tClass);
    }

    @Override
    public double extract(Article<T> article) {
        List<String> contentTokensAfterStemming = article.getContentTokensAfterStemming();
        List<String> contentTokensAfterStemmingTenPercent = contentTokensAfterStemming.subList(0, (int) ( contentTokensAfterStemming.size() / 10.0));
        return numberOfKeyWords.countAllKeyWords(contentTokensAfterStemmingTenPercent, this.wordHolder);
    }

        @Override
            public String description() {
                return "Liczba wszystkich słów kluczowych w 10% pierwszych procentach tekstu";
            }
}
